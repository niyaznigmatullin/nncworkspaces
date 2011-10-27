program _0371;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const p:array[1..40,1..2] of integer=((220,284),
(1184,1210),
(2620,2924),
(5020,5564),
(6232,6368),
(10744,10856),
(12285,14595),
(17296,18416),
(63020,76084),
(66928,66992),
(67095,71145),
(69615,87633),
(79750,88730),
(100485,124155),
(122265,139815),
(122368,123152),
(141664,153176),
(142310,168730),
(171856,176336),
(176272,180848),
(185368,203432),
(196724,202444),
(280540,365084),
(308620,389924),
(319550,430402),
(356408,399592),
(437456,455344),
(469028,486178),
(503056,514736),
(522405,525915),
(600392,669688),
(609928,686072),
(624184,691256),
(635624,712216),
(643336,652664),
(667964,783556),
(726104,796696),
(802725,863835),
(879712,901424),
(898216,980984));

var n,m:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    close(input);
  end;

procedure done;
var i:integer; ok:boolean;
  begin
    ok:=false;
    for i:=1 to 40 do
      begin
        if (p[i][1]>=n) and (p[i][1]<=m) and (p[i][2]>=n) and (p[i][2]<=m) then
          begin
            ok:=true;
            writeln(p[i][1],' ',p[i][2]);
          end;
      end;
    if not ok then write('Absent');
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end.
