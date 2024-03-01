----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/03/2023 08:10:27 AM
-- Design Name: 
-- Module Name: Control - Behavioral
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

entity Control is
  Port (
  rd: in std_logic;
  wr: in std_logic;
  clk: in std_logic;
  rst: in std_logic;
  empty: out std_logic;
  full: out std_logic;
  rdinc: out std_logic;
  wrinc: out std_logic);
  
  
end Control;

architecture Behavioral of Control is
signal fifoCount: integer := 0;
signal copyFull, copyEmpty: std_logic;
begin
process (clk)
begin
if rising_edge(clk) then
    if rst = '1' then 
        fifoCount <= 0;
    elsif rd = '1' and wr = '0' then
        if copyEmpty /= '1' then
            fifoCount <= fifoCount - 1;
        end if;
    elsif rd = '0' and wr = '1' then
        if copyFull /= '1' then
            fifoCount <=fifoCount + 1;
        end if;
    end if; -- rst
end if; -- ckl

end process;

rdinc <= '1' when fifoCount /= 0 and rd ='1' and copyEmpty /='1'  else '0';
wrinc <= '1' when fifoCount /= 8 and wr = '1' and copyFull /='1' else '0';
empty <= '1' when fifoCount = 0 else '0';
copyEmpty <= '1' when fifoCount = 0 else '0';
full <= '1' when fifoCount >= 8 else '0';
copyFull <= '1' when fifoCount >= 8 else '0';

 


end Behavioral;
