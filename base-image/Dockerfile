
# base

FROM openjdk:19-alpine
RUN apk update && apk add bash tar wget

# setup prometheus metric exporter

RUN wget https://github.com/prometheus/node_exporter/releases/download/v1.5.0/node_exporter-1.5.0.linux-386.tar.gz
RUN tar xvfz ./node_exporter-1.5.0.linux-386.tar.gz
EXPOSE 9100

# entrypoint

COPY commands.sh ./commands.sh
RUN ["chmod", "+x", "./commands.sh"]
ENTRYPOINT ["./commands.sh"]
