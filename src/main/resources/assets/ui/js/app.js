'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('strengthsApp', ['ngRoute', 'strengthsApp.services']);

app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/strengths/:strengthId?', {
            templateUrl: 'templates/strengths.html',
            controller: 'StrengthsController'
        });
        $routeProvider.when('/people/:personId?', {
            templateUrl: 'templates/people.html',
            controller: 'PeopleController'
        });
        $routeProvider.otherwise({redirectTo: '/strengths'});
}]);

app.controller('StrengthsController', function($scope,
                                               $routeParams,
                                               StrengthFactory,
                                               PersonFactory,
                                               $location) {
    $scope.strengths = StrengthFactory.query();
    $scope.chosenStrength = undefined;

    $scope.showStrengthDetail = function(chosenStrength){
        if ($scope.isOpen(chosenStrength)){
            $scope.chosenStrength = undefined;
        } else {
            $scope.chosenStrength = chosenStrength;
            //Get the people that have the strength
            $scope.people = PersonFactory.query( {withStrength: chosenStrength.id } );
        }
    };

    $scope.isOpen = function(chosenStrength){
        return $scope.chosenStrength === chosenStrength;
    };

    $scope.anyItemOpen = function() {
        return $scope.chosenStrength !== undefined;
    };

    if ($routeParams.strengthId != undefined) {
        var strength = StrengthFactory.get({id: $routeParams.strengthId});
        $scope.showStrengthDetail(strength);
    }

});

app.controller('PeopleController', function($scope,
                                            $routeParams,
                                            StrengthFactory,
                                            PersonFactory,
                                            $location) {
    $scope.people = PersonFactory.query();
    $scope.chosenPerson = undefined;

    $scope.showPersonDetail = function(chosenPerson){
        if ($scope.isOpen(chosenPerson)){
            $scope.chosenPerson = undefined;
        } else {
            $scope.chosenPerson = chosenPerson;
        }
    };

    $scope.isOpen = function(chosenPerson){
        return $scope.chosenPerson === chosenPerson;
    };

    $scope.anyItemOpen = function() {
        return $scope.chosenPerson !== undefined;
    };

    if ($routeParams.personId != undefined) {
        var person = PersonFactory.get({id: $routeParams.personId});
        $scope.showPersonDetail(person);
    }

});

