FROM cwds/javajdk
RUN mkdir /opt/forms-api
RUN mkdir /opt/forms-api/logs
ADD config/*.yml /opt/forms-api/
ADD config/shiro.ini /opt/forms-api/config/shiro.ini
ADD build/libs/forms-api-dist.jar /opt/forms-api/forms-api.jar
ADD build/entrypoint.sh /opt/forms-api/
EXPOSE 8080 
RUN chmod +x /opt/forms-api/entrypoint.sh
WORKDIR /opt/forms-api
CMD ["/opt/forms-api/entrypoint.sh"]