function fizzbuzz(){
    var input = document.getElementById("messageInput").value;
    document.getElementById("tect").innerHTML ="";
    var i=1;
    while(i<=input&&i<101){
        if(!(i%3==0)&&!(i%5==0)){
            //print i
            document.getElementById("tect").innerHTML += i;
        }else{
            if(i%3==0){
                document.getElementById("tect").innerHTML += "fizz";
            }
            if(i%5==0){
                //print buzz
                document.getElementById("tect").innerHTML += "buzz";
            }
        }
        document.getElementById("tect").innerHTML += "<br>";
        i++;
    }
}