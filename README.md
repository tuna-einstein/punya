# How to import to eclipse?
./gradlew eclipseWtp
Eclipse -> File -> Import -> General -> Existing Projects into Workspace
Choose punya folder

Right click on project -> Properties -> Project Facets 
Check 'Dynamic Web Module', ' Google App Engine', 'Java' and 'JPA'.
Follow, https://cloud.google.com/appengine/docs/java/webtoolsplatform

# How to run in eclipse?
Right click on project -> Run as -> Run on Server -> App Engine

# To see all the tasks
./gradlew tasks

# Running the Local Dev Server
./gradlew appengineRun

# To stop the server
./gradlew appengineStop

# Deploying to App Engine
./gradlew appengineUpdate

# Hot reloading
./gradlew appengineExplodeApp

# More detials
https://github.com/GoogleCloudPlatform/gradle-appengine-plugin
