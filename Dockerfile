# Use an official Tomcat image as the base image
FROM tomcat:10-jdk17

# Set the working directory in the container
WORKDIR /usr/local/tomcat

# Remove default Tomcat webapps
RUN rm -rf webapps/*

# Copy the WAR file into the Tomcat webapps directory
COPY target/rgsm-bot.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]