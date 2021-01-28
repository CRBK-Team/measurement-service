FROM eclipse-mosquitto:2.0.5

COPY entrypoint.sh /

COPY $MOSQUITTO_PASSWORDFILE /

ENTRYPOINT ["sh", "./entrypoint.sh"]

CMD ["/usr/sbin/mosquitto", "-c", "/mosquitto/config/mosquitto.conf"]