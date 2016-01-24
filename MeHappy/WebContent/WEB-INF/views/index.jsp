<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Stateless Auth with Spring Security and JWT</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/home.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="http://getbootstrap.com/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="http://getbootstrap.com/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <div class="jumbotron">
        <div class="container">
            <h1>Spring Security and JWT Tutorial</h1>
            <p>This is a tutorial</p>
          
        </div>
        
        <form class="form-horizontal" action="../rest/service/authenticate" method="post">
			<div class="form-group">
				<label for="username" class="col-sm-3 control-label">User Name:</label>
				<div class="col-sm-9">
					<input id="username" name="username" ng-model="username" type="text" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label">Password:</label>
				<div class="col-sm-9">
					<input id="password" name="password" ng-model="password" type="password" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-9">
					<div class="checkbox">
						<label>
							<input type="checkbox" ng-model="rememberMe"> Remember me
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<input type="submit" value="Log In" class="btn btn-primary" ng-click="login()"/>
				</div>
			</div>
		</form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
