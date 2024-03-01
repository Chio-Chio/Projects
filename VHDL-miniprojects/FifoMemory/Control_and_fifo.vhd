----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/03/2023 08:47:48 AM
-- Design Name: 
-- Module Name: Control_and_fifo - Behavioral
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

entity Control_and_fifo is
  Port (
  rd: in std_logic;
  wr: in std_logic;
  clk: in std_logic;
  rst: in std_logic;
  data_in: in std_logic_vector(7 downto 0);
  empty: out std_logic;
  full: out std_logic;
  data_out: out std_logic_vector (7 downto 0)
   );
end Control_and_fifo;

architecture Behavioral of Control_and_fifo is
component Control is
  Port (
  rd: in std_logic;
  wr: in std_logic;
  clk: in std_logic;
  rst: in std_logic;
  empty: out std_logic;
  full: out std_logic;
  rdinc: out std_logic;
  wrinc: out std_logic);
end component;

component Fifo is
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
end component;
signal rdinc: std_logic;
signal wrinc: std_logic;
begin

p1: Control port map (
    rd => rd, wr=>wr, clk=> clk, rst=>rst, 
    rdinc=>rdinc, wrinc=>wrinc, empty=>empty, 
    full=>full);

p2: Fifo port map (
    rdinc=>rdinc, wrinc=>wrinc, data_in=>data_in, rd=>rd, 
    wr=>wr, clk => clk, rst=>rst, data_out=>data_out
    ); 
end Behavioral;
