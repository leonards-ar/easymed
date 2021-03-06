// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = "turns" // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

//Default UserDetail
grails.plugin.springsecurity.controllerAnnotations.security.defaultRole = "ROLE_USER"
grails.plugin.springsecurity.filterChain.chainMap = [
        '/api/**': 'JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter',  // Stateless chain
        '/**'    : 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'                                                                          // Traditional chain
]
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        '/'  : ['permitAll'],
        '/**': ['permitAll']

]
grails.plugin.springsecurity.password.algorithm = 'SHA-256'
grails.plugin.springsecurity.password.hash.iterations = 1
grails.plugin.springsecurity.userLookup.userDomainClassName="com.mindpool.easymed.domain.User"
grails.plugin.springsecurity.userLookup.authorityJoinClassName="com.mindpool.easymed.domain.UserRole"
grails.plugin.springsecurity.authority.className="com.mindpool.easymed.domain.Role"
grails.plugin.springsecurity.rest.login.active = true
grails.plugin.springsecurity.rest.login.endpointUrl = "/api/login"
grails.plugin.springsecurity.rest.logout.endpointUrl = "/api/logout"
grails.plugin.springsecurity.dao.hideUserNotFoundExceptions = false
grails.plugin.springsecurity.rest.login.failureStatusCode = 401
grails.plugin.springsecurity.rest.login.useJsonCredentials = true
grails.plugin.springsecurity.rest.token.validation.enableAnonymousAccess = true
grails.plugin.springsecurity.rest.login.usernamePropertyName='dni'
grails.plugin.springsecurity.rest.login.passwordPropertyName='password'
grails.plugin.springsecurity.rest.oauth.frontendCallbackUrl =  { String tokenValue -> "htto://www.clarin.com?#token=${tokenValue}" }
grails.plugin.springsecurity.rest.oauth.google.client = org.pac4j.oauth.client.Google2Client
grails.plugin.springsecurity.rest.oauth.google.key = '944878555699-v6kkiidsul44o4jgt5vba2m4kjhetad1.apps.googleusercontent.com'
grails.plugin.springsecurity.rest.oauth.google.secret = 'Am0wjjZayl_IgHt9zoNLvd0D'
grails.plugin.springsecurity.rest.oauth.google.scope = org.pac4j.oauth.client.Google2Client.Google2Scope.EMAIL_AND_PROFILE
grails.plugin.springsecurity.rest.oauth.google.defaultRoles = ['ROLE_USER', 'ROLE_GOOGLE']
grails.plugin.springsecurity.rest.oauth.facebook.client = org.pac4j.oauth.client.FacebookClient
grails.plugin.springsecurity.rest.oauth.facebook.key = '1019878014769366'
grails.plugin.springsecurity.rest.oauth.facebook.secret = 'a22eef387b305fc451f6c0c22ce3babc'
grails.plugin.springsecurity.rest.oauth.facebook.scope = 'email,user_location'
grails.plugin.springsecurity.rest.oauth.facebook.fields = 'id,name,first_name,middle_name,last_name,username,picture'
grails.plugin.springsecurity.rest.oauth.facebook.defaultRoles = ['ROLE_USER', 'ROLE_FACEBOOK']
grails.plugin.springsecurity.useSecurityEventListener = true
grails.plugin.springsecurity.onAbstractAuthenticationFailureEvent = {
    e, appCtx ->
        System.out.println("!!!!!!!!!!!!!!!!!!!" + e)
}
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }

}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    debug  'org.springframework.security',
            "org.pac4j",
            "grails.plugin.springsecurity"
    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

grails.cache.config = {
    cache {
        name 'user'
        eternal false
        timeToLiveSeconds 7200
        overflowToDisk false
        maxElementsInMemory 1000
        maxElementsOnDisk
        memoryStoreEvictionPolicy 'LRU'

    }
}

grails.mail.host = "mail.mindpool-it.com"
grails.mail.port = 587
grails.mail.disabled = false
grails.mail.username = "labs+mindpool.com.ar"
grails.mail.password = "java1234"
grails.mail.props = [
        "mail.debug":"true",
        "mail.smtp.auth":"true",
//        "mail.smtp.socketFactory.port":"587",
//        "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
        "mail.smtp.socketFactory.fallback":"false"
]

/********************************************************
 ************** CORS Plugin Configuration   *************
 *******************************************************/
cors.enabled = true
cors.url.pattern = '/api/*,/i18n/*,/oauth/*'