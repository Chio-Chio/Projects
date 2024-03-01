library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_arith.all; 
use ieee.std_logic_unsigned.all;

entity block64x8 is
  Port (
  a: in std_logic_vector (5 downto 0);
  din: in std_logic_vector (7 downto 0);
  dout: out std_logic_vector (7 downto 0);
  cs: in std_logic;
  wr: in std_logic );
end block64x8;

architecture Behavioral of block64x8 is
type mem_type is array (0 to 63) of std_logic_vector(7 downto 0);
  signal mem : mem_type := (
     others => x"99"
   );
begin

process (cs, wr)

begin 
    if cs = '0' and wr = '0' then
        -- scrie in mem la addr la mem
        mem(conv_integer(a)) <= din;      
    end if;
    
    if cs = '0' and wr = '1' then
        -- citire din mem pus in data out
        dout <= mem(conv_integer(a));
    end if;
end process;

end Behavioral;
