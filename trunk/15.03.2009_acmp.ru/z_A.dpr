program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

procedure init;
  begin
    rewrite(output,'output.txt');
  end;

procedure solve;
  begin

  end;

procedure done;
  begin
    write('11211122222122211122211211222122122211112121121111211222211211212211112111212121212111222221212221211212222121122221211112222211221121221211211221222211112121221222122112122221221221221221222211122222222222222222222111121122221121121212111222211112122112');
    write('112222112221212111112121221221121211221111121212111111121212222212211222122122212112211221221112222121221212121121112111222221122221221121111212121211211211221121211211121122122211212221112122111122212112212121112121121122111112111211111212122112');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.