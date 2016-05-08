var host = "";

function getURI(relativeURL) {
    return host + relativeURL;
}

angular.module('easyMed', ['ui.bootstrap','ui.utils','ui.router','ngCookies','ngAnimate','ngStorage','blockUI','pascalprecht.translate','jlareau.pnotify','app.config']);

var messages = null;

var promiseTranslation = null;

var tabSetIndex = 0;

var messagesLoadedQueue = [];
var sessionLoadedQueue = [];
var applicationInitializedQueue = [];
var isMessagesLoaded = false;
var isSessionLoaded = false;
var isApplicationInitialized = false;

function onMessagesLoaded(fn){
  messagesLoadedQueue.push(fn);
}

function onSessionLoaded(fn){
  sessionLoadedQueue.push(fn);
}

function onApplicationInitialized(fn){
  applicationInitializedQueue.push(fn);
}

function showLoading(){
  setTimeout(function(){$(".page-loading-container").fadeIn(750, function(){
    $(".content").fadeOut(750);

   });}, 100);
}

function hideLoading(){
  setTimeout(function(){$(".content").fadeIn(750, function(){
    $(".page-loading-container").fadeOut(750);
   });}, 100);
   $(".content").display="block";
   $("#page-loading").display="none";
}

function applicationInitialized(){
  if(isMessagesLoaded && isSessionLoaded){
    // console.error("On application initialized");
    while(applicationInitializedQueue.length > 0 ){
      var func= applicationInitializedQueue.pop();
      func.call();
    }
    isApplicationInitialized = true;

    console.log("!!!!!!Hide Loading Carajo!!!!!!!");
    hideLoading();
  }


}

function sessionLoaded(){

  if(!isSessionLoaded){
  // console.error("On session loaded");
  while(sessionLoadedQueue.length > 0 ){
    var func= sessionLoadedQueue.pop();
    func.call();
  }
  isSessionLoaded = true;
  console.log("!!!!!!APP Initialized");
  applicationInitialized();
  }
}

function messagesLoaded(){
  if(!isMessagesLoaded){
  // console.error("On messages loaded");
  while(messagesLoadedQueue.length > 0 ){
    var func= messagesLoadedQueue.pop();
    func.call();
  }
  isMessagesLoaded = true;
  applicationInitialized();
}
}

angular.module('easyMed').factory('lifeCycleHelper', function () {
    var lifeHelper = {};

    lifeHelper.onMessagesLoaded = onMessagesLoaded;
    lifeHelper.onSessionLoaded = onSessionLoaded;
    lifeHelper.onApplicationInitialized = onApplicationInitialized;

    lifeHelper.applicationInitialized = applicationInitialized;
    lifeHelper.sessionLoaded = sessionLoaded;
    lifeHelper.messagesLoaded = messagesLoaded;

    return lifeHelper;
});

angular.module('easyMed').config(function($stateProvider, $urlRouterProvider,configuration) {

    host = configuration.host;

    $stateProvider.state('login', {
        url: '/login',
        templateUrl: 'app/login/login.html'
    });
    /* Add New States Above */
    $urlRouterProvider.otherwise('/home');

});

angular.module('easyMed').run(function($rootScope, $location, $cookies, $http, $localStorage, $q, uiHelper,loginService) {

    $rootScope.safeApply = function(fn) {
        var phase = $rootScope.$$phase;
        if (phase === '$apply' || phase === '$digest') {
            if (fn && (typeof(fn) === 'function')) {
                fn();
            }
        } else {
            this.$apply(fn);
        }
    };

    function checkLogin() {
        // var restrictedPage = $.inArray($location.path(), ['/login', '/logout']) === -1;
        // var isConfirmPassword = $location.path().indexOf('/confirmPassword') >= 0;
        //
        //
        // var isAdminPage = $location.path().indexOf('/administration')>=0;
        //
        // if(isConfirmPassword){
        //   return;
        // }
        //
        // loginService.refreshToken().then(function(currentUser){
        //   sessionLoaded();
        //   var userIsAdmin = currentUser.roles.indexOf("ROLE_ADMIN") >= 0;
        //
        //   if(!restrictedPage || !userIsAdmin && isAdminPage ){
        //     $location.path("/");
        //   }
        // }, function(){
        //     isSessionLoaded = false;
        //     loginService.logout();
        //
        //     if(!restrictedPage){
        //       hideLoading();
        //     }
        //     else{
        //     $location.path("/login");
        //     }
        // });
        console.log("La concha de tu madre");
    }



    // $rootScope.$on('$locationChangeStart', function(event, next, current) {
    //   var restrictedPage = $.inArray($location.path(), ['/login', '/logout']) === -1;
    //   var isConfirmPassword = $location.path().indexOf('/confirmPassword') >= 0;
    //
    //   if(isConfirmPassword){
    //     return;
    //   }
    //
    //   if(restrictedPage && !isApplicationInitialized){
    //       event.preventDefault();
    //       // Keep track of which location the user was about to move to.
    //       var targetPath = $location.path();
    //       var targetSearch = $location.search();
    //       var targetHash = uiHelper.generateUUID().hashCode();
    //
    //      onApplicationInitialized(function(){
    //
    //
    //        checkLogin();
    //        $location
    //            .path( targetPath )
    //            .search( targetSearch )
    //            .hash( targetHash )
    //        ;
    //      });
    //
    //        loginService.refreshToken().then(sessionLoaded, function(){
    //          $location.path( "/login" ).hash( targetHash );
    //        });
    //   }
    //   else{
    //     checkLogin();
    //   }
    //
    // });

    setInterval(checkLogin, 5000);


});

// Translate provider configuration
angular.module('easyMed').config(['$translateProvider', function($translateProvider, uiHelper, $timeout) {
    // Adding a translation table for the English language
    var messagesUrl = getURI("i18n/messages.json");
      $.getJSON(messagesUrl, function(result) {
          messages = result;
          $translateProvider.translations('en', messages);
          messagesLoaded();
      });

      // Adding a translation table for Spanish
      messagesUrl = getURI("i18n/messages_es.json");
      $.getJSON(messagesUrl, function(result){
        messages = result;
        $translateProvider.translations('es', messages);
      });

      // Tell the module what language to use by default
      $translateProvider.preferredLanguage('es');
}]);
