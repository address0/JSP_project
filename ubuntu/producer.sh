#!/bin/bash

QUEUE_NAME="capture_queue"
INPUT_FILE="urls.txt"
START_LOG="start_time.log"

# 큐 초기화
redis-cli DEL "$QUEUE_NAME" > /dev/null

# 시작 시간 기록
date "+%Y-%m-%d %H:%M:%S" > "$START_LOG"

# 상품번호 → URL 변환 후 push
while IFS= read -r id; do
  [[ -z "$id" ]] && continue  # 빈 줄 무시
  url="https://www.tmon.co.kr/deal/${id}"
  redis-cli LPUSH "$QUEUE_NAME" "$url" > /dev/null
done < "$INPUT_FILE"

echo "큐 등록 완료 (${QUEUE_NAME})"