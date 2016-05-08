angular.module('easyMed').controller('LoginCtrl',function($scope, uiHelper, $location, loginService, flashService, lifeCycleHelper,$modal){
  var vm = $scope;
  uiHelper.setTitle("page.login.title");

  vm.loginModel = {
    username: "",
    password: ""
  };

  vm.login = function() {
    vm.dataLoading = true;
    loginService.login(vm.loginModel.username, vm.loginModel.password, vm.findOrganization(vm.loginModel.organizationId)).
    then(function(data) {
        vm.dataLoading = false;
        lifeCycleHelper.sessionLoaded();
        $location.path('/home');
    }, function(data) {
        if (data.status === 401) {
            flashService.showError({
                "code": 401,
                "message": "The email and password provider do not match. Please review the credentials and try again."
            });
        } else {
            flashService.handleError(data);
        }

        vm.dataLoading = false;
    });
};



});
