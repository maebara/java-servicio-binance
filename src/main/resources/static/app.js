var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation-1").show();
        $("#conversation").show();
        $("#conversation2").show();
        $("#conversation3").show();
        $("#conversation4").show();
        $("#conversation5").show();
        $("#conversation6").show();
        $("#conversation7").show();
    } else {
        $("#conversation-1").show();
        $("#conversation").hide();
        $("#conversation2").hide();
        $("#conversation3").hide();
        $("#conversation4").hide();
        $("#conversation5").hide();
        $("#conversation6").hide();
        $("#conversation7").hide();
    }
    $("#userinfo").html("");

}


function drawAll(json){
    console.log("HOLAAAA" + json)
    json.forEach(data => {
        if(data != null){
            let id = hashId(data.id);
            if(data.dto != null && id != null){
                showGreeting(data.dto, id);
            }
        }
    })
}

function hashId(id){
    let response = null;
    switch (id) {
        case -1:
            response ="#5Min";
            break;
        case 0:
            response ="#10Min";
            break;
        case 1:
            response ="#30Min";
            break;
        case 2:
            response ="#1Hr";
            break;
        case 3:
            response ="#3Hr";
            break;
        case 4:
            response ="#5Hr";
            break;
        case 5:
            response ="#12Hr";
            break;
        case 6:
            response ="#24Hr";
            break;
        default:
            break;
    }

    return response;
}

function connect() {
    var socket = new SockJS('/websocket-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user/5Min', function (greeting) {
            showGreeting(JSON.parse(greeting.body), "#5Min");
        });
        stompClient.subscribe('/topic/user/10Min', function (greeting) {
            showGreeting(JSON.parse(greeting.body), "#10Min");
        });
        stompClient.subscribe('/topic/user/30Min', function (greeting) {
            showGreeting(JSON.parse(greeting.body), "#30Min");
        });
        stompClient.subscribe('/topic/user/1Hr', function (greeting) {
            showGreeting(JSON.parse(greeting.body), "#1Hr");
        });
        stompClient.subscribe('/topic/user/3Hr', function (greeting) {
            showGreeting(JSON.parse(greeting.body), "#3Hr");
        });
        stompClient.subscribe('/topic/user/5Hr', function (greeting) {
            showGreeting(JSON.parse(greeting.body), "#5Hr");
        });
        stompClient.subscribe('/topic/user/12Hr', function (greeting) {
            showGreeting(JSON.parse(greeting.body), "#12Hr");
        });
        stompClient.subscribe('/topic/user/24Hr', function (greeting) {
            showGreeting(JSON.parse(greeting.body), "#24Hr");
        });
    });


}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/user", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message, id) {
    let html = "";
    message.forEach(c => {
        let url = "https://www.binance.com/es/trade/";
        let code = c.cryptocurrency.symbol.split("USDT")[0] + "_" + "USDT";
        html += `
            <tr><td><a href="${url}${code}" target="_blank">${c.cryptocurrency.symbol}</a></td><td>${c.changePercent}</td></tr>
          `
    })

    $(id).html(html);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    console.log("HOLA??")
    fetch("/api/all").then(res => res.json()).then(json => drawAll(json));


    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});