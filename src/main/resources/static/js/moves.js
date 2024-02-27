let alphabet = 'abcdefghijklmnopqrstuvwxyz'.split('');
  function getLetterByNumber(number) {
       return String.fromCharCode(64 + number);
   }
   function getLetterMove(numericMove){
        var from=numericMove.from;
        var to=numericMove.to;
        var from=alphabet[numericMove.from.x-1]+numericMove.from.y;
        var to=alphabet[numericMove.to.x-1]+numericMove.to.y;
        return from+"-"+to;
    }

   function getNumMove(start, end) {
       const letterToNumber = {
           'a': 1, 'b': 2, 'c': 3, 'd': 4, 'e': 5, 'f': 6, 'g': 7, 'h': 8
       };

       const from = {
           x: letterToNumber[start[0]],
           y: parseInt(start[1])
       };

       const to = {
           x: letterToNumber[end[0]],
           y: parseInt(end[1])
       };

       return { from, to };
   }
