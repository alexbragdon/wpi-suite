var configuration = {
	username: "",
	project: "",
	session: 0
};

function handleLoad() {
	configureFromFragment();
	fillFromConfiguration();
}

function run() {
	configureFromFragment();
	configureFromFields();
	var password = document.getElementById("password").value;
	login(configuration.username, password, function(response) {
		switchProject(configuration.project, function(response) {
			getPlanningPokerSession(configuration.session, function(response) {
				var session = JSON.parse(response)[0];
				var output = "";
				session.RequirementEstimates.forEach(function(value, index) {
					output += '<div class="requirement">';
					output += "<h2>" + value.name + "</h2>";
					output += "<p>" + value.description + "</p>";
					output += '<input type="number" id="vote-' + index + '" />';
					output += '<input type="button" value="Vote" onclick="vote(' + index + ');" />';
					output += '</div>';
				});
				document.getElementById("output").innerHTML = output;
			});
		});
	});
}

/* CONFIG FUNCTIONS */

function getFromFragment(key) {
	if (window.location.hash) {
		var matches = window.location.hash.match(new RegExp(key + "=([^&]+)"));
		if (matches !== null) {
			return matches[1];
		} else {
			return null;
		}
	}
}

function configureFromFragment() {
	var username = getFromFragment("username");
	var project = getFromFragment("project");
	var session = getFromFragment("session");
	if (username !== null) {
		configuration.username = username;
	}
	if (project !== null) {
		configuration.project = project;
	}
	if (session !== null) {
		var sessionNum = parseInt(session, 10);
		if (sessionNum !== NaN) {
			configuration.session = sessionNum;
		}
	}
}

function configureFromFields() {
	var username = document.getElementById("username").value;
	var project = document.getElementById("project").value;
	configuration.username = username;
	configuration.project = project;
}

function fillFromConfiguration() {
	document.getElementById("username").value = configuration.username;
	document.getElementById("project").value = configuration.project;
}

/* NETWORK FUNCTIONS */

function login(username, password, callback) {
	console.log("Logging in as " + username);
	var auth = "Basic " + window.btoa(username + ":" + password);
	var xhr = createXHR(callback);
	xhr.open("POST", "/WPISuite/API/login");
	xhr.setRequestHeader("Authorization", auth);
	xhr.send();
}

function switchProject(project, callback) {
	console.log("Switching to project " + project);
	var xhr = createXHR(callback);
	xhr.open("PUT", "/WPISuite/API/login");
	xhr.send(project);
}

function getPlanningPokerSession(sessionIndex, callback) {
	console.log("Getting planning poker sessions");
	var xhr = createXHR(callback);
	xhr.open("GET", "/WPISuite/API/planningpoker/planningpokersession/" + sessionIndex);
	xhr.send(project);
}

function createXHR(callback) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.status == 200 && this.readyState == 4) {
			callback(this.responseText);
		}
	};
	return xhr;
}