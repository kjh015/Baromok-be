FROM nginx:alpine
# 기존 설정을 삭제하고 새로 만든 설정을 복사
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80