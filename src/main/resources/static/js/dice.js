function changeDice(diceId,color,pieceLetter) {
const dice = document.getElementById(diceId);
  if(pieceLetter==null){
    dice.style.visibility='hidden';
  }else{
    dice.style.visibility='visible';
  }
  const piece = dice.querySelector('.chess-piece');
  piece.setAttribute('src', '/img/chesspieces/wikipedia/'+color+pieceLetter+'.png');
}