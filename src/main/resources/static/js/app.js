angular.module('app', [ 'ngRoute' ]).config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'home.html',
        controller : 'home'
    }).when('/login', {
        templateUrl : 'login.html',
        controller : 'navigation'
    }).when('/products', {
        templateUrl : 'products.html',
        controller : 'products'
    }).when('/companies', {
        templateUrl : 'companies.html',
        controller : 'companies'
    }).when('/sales', {
        templateUrl : 'sales.html',
        controller : 'sales'
    }).otherwise('/');

}).controller('navigation', function($rootScope, $scope, $http, $location, $route) {
    $scope.tab = function(route) {
        return $route.current && route === $route.current.controller;
    };

    var authenticate = function(callback) {
        $http.get('user').success(function(data) {
            if (data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }).error(function() {
            $rootScope.authenticated = false;
            callback && callback();
        });

    }

    //authenticate();

    $scope.credentials = {};
    $scope.login = function() {
        $http.post('login', $.param($scope.credentials), {
            headers : {
                "content-type" : "application/x-www-form-urlencoded"
            }
        }).success(function(data) {
            authenticate(function() {
                if ($rootScope.authenticated) {
                    console.log("Login succeeded")
                    $location.path("/");
                    $scope.error = true;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed with redirect")
                    $location.path("/login");
                    $scope.error = true;
                    $rootScope.authenticated = false;
                }
            });
        }).error(function(data) {
            console.log("Login failed")
            $location.path("/login");
            $scope.error = true;
            $rootScope.authenticated = false;
        })
    };

    $scope.logout = function() {
        $http.post('logout', {}).success(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        }).error(function(data) {
            console.log("Logout failed")
            $rootScope.authenticated = false;
        });
    }

}).controller('home', function($scope, $http) {
}).controller('products', function($scope, $http) {
    $http.get('api/products').success(function(data) {
        $scope.products = _.sortBy(data, 'name');
    });
}).controller('companies', function($scope, $http) {
    $http.get('api/companies').success(function(data) {
        $scope.companies = _.sortBy(data, 'name');
    });
    $http.get('api/users').success(function(data) {
        $scope.users = _.sortBy(data, 'lastName');
    });
}).controller('sales', function($scope, $http) {
    $scope.activeProduct = null;

    $http.get('api/products').success(function(data) {
        $scope.products = _.sortBy(data, 'name');
    });
    
    $scope.viewProductSales = function (product) {
        $scope.activeProduct = null;
        $scope.sales = null;

        $http.get('api/products/' + product.id + '/monthly-sales').success(function(data) {
            $scope.activeProduct = product;
            $scope.sales = _.sortBy(data, ['year', 'month']);
        });
    };
});