'use strict'
angular.module('tccApp').service('homeService', ['Restangular', function (Restangular) {
	
    this.api = function () {
        return Restangular.one('home');
    };
	
}]);
