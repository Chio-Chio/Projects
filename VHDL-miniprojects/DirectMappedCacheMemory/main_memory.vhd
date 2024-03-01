library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity main_memory is
  Port (
  rd: in std_logic; -- active low
  wr: in std_logic;
  data_in: in std_logic_vector (7 downto 0);
  address: in std_logic_vector (15 downto 0);
  data_out0: out std_logic_vector (7 downto 0);
  data_out1: out std_logic_vector (7 downto 0);
  data_out2: out std_logic_vector (7 downto 0);
  data_out3: out std_logic_vector (7 downto 0)
   );
end main_memory;

architecture Behavioral of main_memory is
type mainMemory_type is array (0 to 1023) of std_logic_vector(7 downto 0);
  signal mm : mainMemory_type := (
     others => x"00"
   ); -- address is the index of the array, values inside the array is data

begin
process (rd)
    variable i: integer := conv_integer(address);
begin
    if rd = '0' then
        data_out0 <= mm(i);
        data_out1 <= mm(i + 1);
        data_out2 <= mm(i + 2);
        data_out3 <= mm(i + 3);
    elsif wr = '0' then
        mm(i) <= data_in;
    end if;
end process;
end Behavioral;
