angular.module('app.config', [])
  .constant('configuration', {
    host: 'http://localhost:8080/turns/',
    securityGroupProtocols: 'tcp,udp,icmp',
    version: '0.0.1',
    lastUpdate: '2016-05-04',
    trackingId: 'UA-76346601-4',
    captchaSiteKey: '6LeSYB8TAAAAAEUCuC0wT-O2seZ0SdhyB5uK7d8t'
  });
