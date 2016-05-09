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

  vm.popup = {
    opened: false
  };

  vm.open = function() {
    console.log("La concha de tu madre!!!! Abrite y no de piernas.....");
    vm.popup.opened = true;
  };

  // Disable weekend selection
  function disabled(data) {
    var date = data.date,
      mode = data.mode;
    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
  }

  vm.dateOptions = {
    dateDisabled: false,
    formatYear: 'yyyy',
    maxDate: new Date(),
    minDate: new Date(1900, 1, 1),
    startingDay: 1
  };
  vm.captcha=null;
  // vm.today;
  //
  // vm.clear = function() {
  //   vm.dt = null;
  // }
  //
  // vm.inlineOptions = {
  //   customClass: getDayClass,
  //   minDate: new Date(),
  //   showWeeks: true
  // };
  //

  //
  // // Disable weekend selection
  // function disabled(data) {
  //   var date = data.date,
  //     mode = data.mode;
  //   return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
  // }
  //
  // vm.toggleMin = function() {
  //   vm.inlineOptions.minDate = vm.inlineOptions.minDate ? null : new Date();
  //   vm.dateOptions.minDate = vm.inlineOptions.minDate;
  // };
  //
  // vm.toggleMin();
  //

  //
  // vm.setDate = function(year, month, day) {
  //   vm.dt = new Date(year, month, day);
  // };
  //
  // vm.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  // vm.format = vm.formats[0];
  // vm.altInputFormats = ['M!/d!/yyyy'];
  //
  // vm.popup1 = {
  //   opened: false
  // };
  //
  // var tomorrow = new Date();
  // tomorrow.setDate(tomorrow.getDate() + 1);
  // var afterTomorrow = new Date();
  // afterTomorrow.setDate(tomorrow.getDate() + 1);
  // vm.events = [
  //   {
  //     date: tomorrow,
  //     status: 'full'
  //   },
  //   {
  //     date: afterTomorrow,
  //     status: 'partially'
  //   }
  // ];
  //
  // function getDayClass(data) {
  //   var date = data.date,
  //     mode = data.mode;
  //   if (mode === 'day') {
  //     var dayToCheck = new Date(date).setHours(0,0,0,0);
  //
  //     for (var i = 0; i < vm.events.length; i++) {
  //       var currentDay = new Date(vm.events[i].date).setHours(0,0,0,0);
  //
  //       if (dayToCheck === currentDay) {
  //         return vm.events[i].status;
  //       }
  //     }
  //   }
  //
  //   return '';
  // }
});
