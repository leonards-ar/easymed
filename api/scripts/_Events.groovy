eventCompileStart = {kind ->
    println "Demand Planner Compile start with ${kind}"
//    kind.variables.each { println "key: ${it.key}, type: ${kind.getAt(it.key).getClass()}" }
    File baseDir = new File(kind.getAt("basedir"));

    File jsonTranslationsDirectory = new File(baseDir.absolutePath + "/web-app/i18n")

    if( !jsonTranslationsDirectory.exists() ) {
        // Create all folders up-to and including B
        jsonTranslationsDirectory.mkdirs()
    }

    println ("Deleting all cached i18n json files from " + jsonTranslationsDirectory.absolutePath)
    jsonTranslationsDirectory.eachFile { file -> file.delete() }

    File translationDirectory = new File(baseDir.absolutePath + "/grails-app/i18n")
    translationDirectory.eachFile { file ->
        org.codehaus.groovy.grails.web.json.JSONObject translationObject = new org.codehaus.groovy.grails.web.json.JSONObject();
        file.eachLine { line ->
            if (!line.isEmpty()) {
                String[] translation = line.split("=")
                if (translation.length == 2) {
                    translationObject.put(translation[0], translation[1])
                }
            }
        }

        String fileName = org.apache.commons.io.FilenameUtils.getBaseName(file.name) + ".json"
        println ("Creating cache translation file  ${fileName} to " + jsonTranslationsDirectory.absolutePath)
        File newJsonTransfileFile = new File(jsonTranslationsDirectory.absolutePath + "/" + fileName)
        def os = newJsonTransfileFile.newOutputStream();
        os.write(translationObject.toString().getBytes())
        os.close()
    }
}