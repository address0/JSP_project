#!/bin/bash

QUEUE_NAME="capture_queue"
HTML_QUEUE="html_queue"
HTML_DIR="./htmls"

mkdir -p "$HTML_DIR"
WORKER_ID=$$
COUNT=0

echo "[NET $WORKER_ID] 시작"

redis-cli DEL "$HTML_QUEUE" > /dev/null

while true; do
  url=$(redis-cli BRPOP "$QUEUE_NAME" 5 | tail -n1)

  if [ -z "$url" ]; then
    echo "[NET $WORKER_ID] 작업 없음, 종료. 총 작업 수: $COUNT"
    break
  fi

  hash=$(echo -n "$url" | md5sum | cut -d' ' -f1)
  html_file="$HTML_DIR/$hash.html"

  if curl -k -L -A "Mozilla/5.0" "$url" -o "$html_file" > /dev/null 2>&1; then
    COUNT=$((COUNT + 1))
    redis-cli LPUSH "$HTML_QUEUE" "$html_file|$url" > /dev/null
    echo "[NET $WORKER_ID] SAVE : $html_file"
  else
    echo "[NET $WORKER_ID] ❌ HTML 다운로드 실패: $url" >> failed.log
    redis-cli LPUSH capture_failed "$url" > /dev/null
  fi
done