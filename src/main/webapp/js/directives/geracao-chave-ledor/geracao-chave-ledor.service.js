'use strict'
angular.module('tccApp').service('geracaoChaveLedorService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('audio');
    };


}]);
