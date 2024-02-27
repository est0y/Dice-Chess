let roomId  =  document.currentScript.getAttribute('roomId');

let stompClient = null;

const chatLineElementId = "chatLine";
const roomIdElementId = "roomId";
const messageElementId = "message";
let boardConfig;
let board;


fetch('http://localhost:8080/csrf')
  .then(response => response.json())
  .then(data => {
    csrfToken = data.token;

 var headers = {};
console.log(document.cookie)
 headers["X-CSRF-TOKEN"] =  csrfToken

    stompClient = Stomp.over(new SockJS('/any'));
    stompClient.connect(headers, (frame) => {
        console.log(`Connected to roomId: ${roomId} frame:${frame}`);
          //stompClient.send("/app/game/id."+roomId+"/move", {}, '{ "from":{"x":1,"y":7},"to":{"x":1,"y":6}}');
          stompClient.subscribe('/user/queue/role/'+roomId, (message) => {
                  jsonMessage=JSON.parse(message.body);
                  const role=jsonMessage.role;
                   stompClient.subscribe('/game/id.'+roomId+'/fen', (message) => setGameState(JSON.parse(message.body)),{ id: "fenTopic"});
                   stompClient.subscribe('/game/id.'+roomId+'/move', (message) => setGameState(JSON.parse(message.body)),{ id: "moveTopic"});

                  if(role=="SPECTATOR"){
                        boardConfig= {
                        orientation: 'white',
                          draggable: false,
                          position: jsonMessage.fen,
                          pieceTheme: '/img/chesspieces/wikipedia/{piece}.png',
                          onDragStart: onDragStart
                        }
                  }else if(role=="WHITE"){
                        boardConfig= {
                        orientation: 'white',
                          draggable: true,
                          position: 'start',
                           onDrop: whiteOnDrop,
                           position: jsonMessage.fen,
                          pieceTheme: '/img/chesspieces/wikipedia/{piece}.png',
                          onDragStart: onDragStart
                        }

                  }else if(role=="BLACK"){
                        boardConfig= {
                        orientation: 'black',
                          draggable: true,
                          position: jsonMessage.fen,
                           onDrop: blackOnDrop,
                          pieceTheme: '/img/chesspieces/wikipedia/{piece}.png',
                          onDragStart: onDragStart
                        }
                  }else{
                    console.log("role not found")
                  }
                   board = Chessboard('myBoard', boardConfig)
                    setDices(jsonMessage);
              });

              stompClient.send('/app/connect/'+roomId, {}, '');
        });

   });


const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function makeMove(move){
    board.move(getLetterMove(move));
}
function setDices(gameState){
  var color;
    if(gameState.turnHolder=="WHITE"){
        color='w';
    }else{
        color='b';
    }
    changeDice("dice1",color,gameState.pieces[0]);
    changeDice("dice2",color,gameState.pieces[1]);
}
function setGameState(gameState){
    makeMove(gameState.move);
    setDices(gameState);
}

