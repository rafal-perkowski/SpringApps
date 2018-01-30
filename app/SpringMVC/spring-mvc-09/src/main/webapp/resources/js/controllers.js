var cartApp = angular.module('cartApp', []);

cartApp.controller('cartCtrl', function($scope, $http) {

    $scope.refreshCart = function(cartId) {
        $http.get('/spring-mvc-09/rest/cart/' + $scope.cartId)
            .success(function(data) {
                $scope.cart = data;
            });
    };

    $scope.clearCart = function() {
        $http.delete('/spring-mvc-09/rest/cart/' + $scope.cartId)
            .success(function(data) {
                $scope.refreshCart($scope.cartId);
            });

    };

    $scope.initCartId = function(cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart($scope.cartId);
    };

    $scope.addToCart = function(productId) {
        $http.put('/spring-mvc-09/rest/cart/add/' + productId)
            .success(function(data) {
                alert("Product Successfully added to the Cart!");
            });
    };
    $scope.removeFromCart = function(productId) {
        $http.put('/spring-mvc-09/rest/cart/remove/' + productId)
            .success(function(data) {
                $scope.refreshCart($scope.cartId);
            });
    };
});
