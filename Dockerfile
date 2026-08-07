
FROM tomcat:9
COPY target/ORSProject-04.war /usr/local/tomcat/webapps/ORSProject-04.war
EXPOSE 8081
CMD ["catalina.sh", "run"]