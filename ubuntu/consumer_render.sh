#!/bin/bash

export QT_QPA_PLATFORM=offscreen

HTML_QUEUE="html_queue"
OUTPUT_DIR="./captures"
SAVE_LOG="save_time.log"

mkdir -p "$OUTPUT_DIR"
WORKER_ID=$$
COUNT=0

echo "[RENDER $WORKER_ID] 시작"

while true; do
  entry=$(redis-cli BRPOP "$HTML_QUEUE" 10 | tail -n1)

  if [ -z "$entry" ]; then
   # echo "[RENDER $WORKER_ID] 작업 없음, 종료. 총 작업 수: $COUNT"
    break
  fi

  html_file="${entry%%|*}"
  url="${entry##*|}"

  if [ ! -f "$html_file" ]; then
    echo "[RENDER $WORKER_ID] ❌ 파일 없음: $html_file ($url)" >> failed.log
    continue
  fi

  filename="${WORKER_ID}_${COUNT}.jpg"
  filepath="$OUTPUT_DIR/$filename"


  if wkhtmltoimage \
    --width 860 \
    --height 700 \
    --zoom 0.75 \
    --disable-javascript \
    --no-stop-slow-scripts \
    --quiet \
    "$html_file" "$filepath"; then
    COUNT=$((COUNT + 1))
    date "+%Y-%m-%d %H:%M:%S" >> "$SAVE_LOG"
  else
    echo "[RENDER $WORKER_ID] ❌ 이미지 변환 실패: $html_file" >> failed.log
  fi
done