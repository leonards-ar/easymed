angular.module('easyMed').factory('loginService',function($q, $http, $rootScope, $timeout, $localStorage, uiHelper) {

	function clearAuthenticationData() {
			$rootScope.globals = {};
			$localStorage.$reset();
			$http.defaults.headers.common.Authorization = '';
	}

	function addAuthenticationData(authData) {
			$rootScope.globals = {
					currentUser: authData,
					datetime: new Date(),
					expiresIn : authData.expires_in
			};

			$localStorage.globals = $rootScope.globals;

			refreshAuthorizationToken();


	}

	function login(dni, password) {
			clearAuthenticationData();
			var deferred = $q.defer();
			var url = uiHelper.getURI("api/login");
			dni = dni;
			var request = {
					"dni": dni,
					"password": password
			};
			$http.post(url, request)
					.success(function(data, status, headers, config) {
							addAuthenticationData(data);

					})
					.error(function(data, status, headers, config) {
							deferred.reject({
									data: data,
									status: status
							});
					});
			return deferred.promise;
	}

	function logout() {
			var deferred = $q.defer();
			try{
				clearAuthenticationData();
				deferred.resolve();
			}
			catch(ex){
				deferred.reject(ex);
			}

			return deferred.promise;
	}
	function refreshAuthorizationToken(){
		if(getCurrentUser().token_type){
				$http.defaults.headers.common['Authorization'] = getCurrentUser().token_type + ' ' + getCurrentUser().access_token;
				return true;
		}
		else{
			return false;
		}

	}
	function refreshToken() {

			var deferred = $q.defer();
			if (sessionEmpty()) {
					deferred.reject();
			} else if (sessionExpired()) {

					var url = uiHelper.getURI("oauth/access_token");
					url += "?grant_type=refresh_token&refresh_token=" + getCurrentUser().refresh_token;

					$http.post(url, {})
							.success(function(data, status, headers, config) {
									addAuthenticationData(data);
							})
							.error(function(data, status, headers, config) {
									deferred.reject({
											data: data,
											status: status
									});
							});
			} else {
					refreshAuthorizationToken();
			}

			return deferred.promise;
	}


	function sessionExpired() {


			var difference = new Date().getTime() - new Date($localStorage.globals.datetime).getTime();

			var secondsDifference = Math.floor(difference / 1000);

			// We are gonna make session expire 100 seconds before the max time
			return secondsDifference >= ($localStorage.globals.expiresIn - 100);
	}

	function sessionEmpty() {
			var result =  $localStorage.globals === undefined || $localStorage.globals.currentUser === undefined || $localStorage.globals.datetime === undefined || $localStorage.globals.currentUser.organization === 456;

			return result;
	}

	function getCurrentUser() {
			if (sessionEmpty()) {
					return {};
			}

			return $localStorage.globals.currentUser;
	}

	var loginService = {};

	loginService.login = login;
	loginService.logout = logout;
	loginService.refreshToken = refreshToken;
	loginService.sessionExpired = sessionExpired;
	loginService.sessionEmpty = sessionEmpty;
	loginService.getCurrentUser = getCurrentUser;

	return loginService;
});
