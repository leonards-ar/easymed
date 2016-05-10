angular.module('easyMed').controller('RegisterCtrl',function($scope){
  var vm = $scope;

  vm.dt = null;
  vm.osList = [{"id":1,"name":'OSDE'},{"id":2,"name":'SWISS'},{"id":3,"name":'GALENO'}];
  vm.filterCondition = {
    "os":null
  };
  vm.today = function() {
    vm.dt = new Date();
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

  // vm.resetCaptcha = function(){
  //   if(vm.captchaControl.reset){
  //     vm.captchaControl.reset();
  //   }
  // };


});
