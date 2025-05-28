#!/bin/bash

echo "캡처 작업 시작: $(date)"

# ===============================
# 1. 시스템 기반 워커 수 계산
# ===============================
CPU_CORES=$(nproc)
TOTAL_MEM_MB=$(free -m | awk '/^Mem:/{print $2}')
EST_PER_WORKER_MB=130
MAX_MEM_WORKERS=$((TOTAL_MEM_MB / EST_PER_WORKER_MB))
CPU_LIMIT=$((CPU_CORES * 4))  # 코어당 최대 3개 워커 허용

if (( MAX_MEM_WORKERS < CPU_LIMIT )); then
  WORKER_COUNT=$MAX_MEM_WORKERS
else
  WORKER_COUNT=$CPU_LIMIT
fi

if (( WORKER_COUNT < 2 )); then
  WORKER_COUNT=2
fi

echo "🧠 시스템 RAM: $TOTAL_MEM_MB MB"
echo "⚙️ CPU 코어 수: $CPU_CORES"
echo "🚀 사용할 워커 수: $WORKER_COUNT"

# ===============================
# 2. tmpfs 마운트
# ===============================
TMPFS_DIR="./captures"
TMPFS_SIZE="2G"
mkdir -p "$TMPFS_DIR"

if mountpoint -q "$TMPFS_DIR"; then
  echo "ℹ️ tmpfs 이미 마운트됨"
else
  echo "📦 tmpfs 마운트 중... (크기: $TMPFS_SIZE)"
  sudo mount -t tmpfs -o size=$TMPFS_SIZE tmpfs "$TMPFS_DIR"
fi

# === html Mount
HTML_TMPFS_DIR="./htmls"
HTML_TMPFS_SIZE="512M"

mkdir -p "$HTML_TMPFS_DIR"

if mountpoint -q "$HTML_TMPFS_DIR"; then
  echo "ℹ️ htmls tmpfs 이미 마운트됨"
else
  echo "📦 htmls tmpfs 마운트 중... (크기: $HTML_TMPFS_SIZE)"
  sudo mount -t tmpfs -o size=$HTML_TMPFS_SIZE tmpfs "$HTML_TMPFS_DIR"
fi


# ===============================
# 3. producer 실행
# ===============================
echo "📤 producer.sh 실행..."
./producer.sh

# ===============================
# 4. 병렬 consumer 실행
# ===============================
HALF_COUNT=$(echo "$WORKER_COUNT * 0.9" | bc)
HALF_COUNT=${HALF_COUNT%.*}
HALF_COUNT2=$((WORKER_COUNT - HALF_COUNT))

echo "▶️ consumer 병렬 실행..."
for i in $(seq 1 "$HALF_COUNT2"); do
  ./consumer_network.sh &
done

# 큐가 채워질 때까지 대기
echo "⏳ html_queue 대기 중..."
while [ "$(redis-cli LLEN html_queue)" -eq 0 ]; do
	 sleep 0.1;
done

echo "✅ html_queue 감지됨. 렌더 시작."
for i in $(seq 1 "$HALF_COUNT"); do
  ./consumer_render.sh &
done

wait
echo "모든 작업 완료!"

# ===============================
# 5. 시간 측정
# ===============================
START_LOG="start_time.log"
SAVE_LOG="save_time.log"

if [[ -f "$START_LOG" && -f "$SAVE_LOG" ]]; then
  START_TIME=$(head -n1 "$START_LOG")
  END_TIME=$(tail -n1 "$SAVE_LOG")

  # 리눅스 기준
  START_UNIX=$(date -d "$START_TIME" +%s)
  END_UNIX=$(date -d "$END_TIME" +%s)
  DURATION=$((END_UNIX - START_UNIX))

  echo "📊 소요 시간 계산 완료"
  echo "🔹 시작 시각: $START_TIME"
  echo "🔹 마지막 저장 시각: $END_TIME"
  echo "⏱ 총 소요 시간: ${DURATION}초"
else
  echo "⚠️ 시간 측정용 로그가 존재하지 않습니다"
fi