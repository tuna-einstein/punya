# How to import to eclipse?
Eclipse -> File -> Import -> gradle -> gradle project

# How to run in eclipse?
From the gradle task console, run appengineRun

# How to stop the server in eclipse?
From the gradle task console, run appengineStop

# To see all the tasks
gradlew tasks

# Running the Local Dev Server
gradlew appengineRun

# To stop the server
gradlew appengineStop

# Deploying to App Engine
gradlew appengineUpdate

# More detials
https://github.com/GoogleCloudPlatform/gradle-appengine-plugin

# When you want to reload classes do a "./gradlew customReload" it will pull any changes you made
# in to your classes and your webapp files (html/css/js and excluding the WEB-INF dir).
gradlew customReload
