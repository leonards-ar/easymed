angular.module('easyMed').controller('LoginCtrl',function($scope, uiHelper, $location, loginService, flashService, lifeCycleHelper){
  var vm = $scope;

  uiHelper.setTitle("page.login.title");

  vm.loginModel = {
    username: "",
    password: ""
  };

  vm.login = function() {
    vm.dataLoading = true;
    loginService.login(vm.loginModel.dni, vm.loginModel.password).
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

/**********************************
*     Register Form               *
**********************************/

  vm.registerModel = {
    dni: "",
    password: "",
    lastName: "",
    firstName: "",
    birth:"",
    os:"",
    confirmPassword: "",
    emai: "",
    phone: "",
    cell: "",
    nationality:"",
    street:"",
    city:"",
    state:"",
    zipCode:""
  };
  vm.open = function($event) {
    $event.preventDefault();
    $event.stopPropagation();

    vm.opened = true;
  };


  // Disable weekend selection
  function disabled(date,mode) {
    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  }

  vm.mode = "year";

  vm.dateOptions = {
    'formatYear': 'yyyy',
    'maxDate': new Date(),
    'minDate': new Date(1900, 1, 1),
    'datepicker-mode':'year'
  };

  vm.captchaControl = {};

});
