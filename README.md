# Java-Twitter-Streamer
Using the Twitter stream api, this project will collect and store a twitter stream based on a term (or multiple terms) into a Json File.

You'll need a Standard Mongo Db installed and running under local host.

Use standard gradle command "gradlew run" will run the program on the command line, or you can run the "gradlew distZip" and it will generate a zipped version, which is a stand alone application.

You'll need to modify the config.template.properties to just config.properties and include your twitter api key.

# Search terms
Set your search terms in the search.txt. Space them on single lines as shown.

If you use the "gradlew distZip" command this will be bundled in. So change them to what you'd like before continuing.
 
# Future stuff
- search.txt external to the distZip
- config.properties external to the distZip