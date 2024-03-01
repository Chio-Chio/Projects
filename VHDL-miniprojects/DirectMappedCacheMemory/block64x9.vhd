library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_arith.all; 
use ieee.std_logic_unsigned.all;

entity block64x9 is
  Port (
  a: in std_logic_vector (5 downto 0);
  din: in std_logic_vector (7 downto 0);
  dout: out std_logic_vector (7 downto 0);
  is_valid: out std_logic; -- 1 for isn't valid
  write_through: out std_logic; -- - for write through
  cs: in std_logic;
  wr: in std_logic );
end block64x9;

architecture Behavioral of block64x9 is
type mem_type is array (0 to 63) of std_logic_vector(7 downto 0);
  signal tags : mem_type := (
     others => x"99"
   );
   
type valid_type is array (0 to 63) of std_logic;
    signal v: valid_type := (
        others => '1'
    );   

begin

process (cs, wr)

begin 
    if cs = '0' and wr = '0' then
        -- scrie in mem la addr la mem
        tags(conv_integer(a)) <= din;  
         
--        write_through <= '0';
        v(conv_integer(a)) <= '0'; 
        
    end if;
    
    if cs = '0' and wr = '1' then
        if v(conv_integer(a)) = '0' then
            -- citire din mem pus in data out
            dout <= tags(conv_integer(a));
            is_valid <= '0';
        else
            is_valid <= '1';
            dout <= (others => 'Z');
        end if;
    end if;
end process;

end Behavioral;
