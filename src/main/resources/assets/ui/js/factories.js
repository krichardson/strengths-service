var factories = angular.module('strengthsApp.services', ['ngResource']);

factories.factory('StrengthFactory', function($resource) {
    return $resource('/api/strengths/:id', {id: '@_id' }, {});
});

factories.factory('PersonFactory', function($resource) {
    return $resource('/api/people/:id', {id: '@_id' }, {});
});