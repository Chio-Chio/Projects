----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/03/2023 08:31:10 AM
-- Design Name: 
-- Module Name: Fifo - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Fifo is
  Port (
  rdinc: in std_logic;
  wrinc: in std_logic;
  data_in: in std_logic_vector(7 downto 0);
  wr: in std_logic;
  rd: in std_logic;
  rst: in std_logic;
  clk: in std_logic;
  data_out: out std_logic_vector (7 downto 0)
   );
end Fifo;

architecture Behavioral of Fifo is

type fifo_type is array(0 to 7) of std_logic_vector(7 downto 0);
signal fifo: fifo_type:=(others =>(others=>'0'));
signal wrptr: integer :=0;
signal rdptr: integer :=0;
begin

process (clk, rst)
begin
 if rising_edge(clk) then
    if rst = '1' then
        rdptr <= 0;
        wrptr <= 0;
    else
        if rdinc ='1' then
            if rdptr = 7 then
                rdptr <= 0;
            else
                rdptr <= rdptr +1;
            end if;
        end if;
        if wrinc ='1' then
            fifo(wrptr) <= data_in;
         
            if wrptr = 7 then
                wrptr <= 0;
            else 
                wrptr <= wrptr + 1;
            end if;
        end if;
    end if;--rst
    --     fifo(wrptr) <= data_in;
  end if; --clk
       
end process;

data_out <= fifo(rdptr) when rdinc = '1';-- else (others=>'Z');
--fifo(wrptr) <= data_in when wr = '1';
--rdptr <= 0 when rst = '1';
--wrptr <= 0 when rst = '1';

end Behavioral;
