----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/05/2023 10:48:46 PM
-- Design Name: 
-- Module Name: main_struct - Behavioral
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

entity main_struct is
  Port (
  data_in: in std_logic_vector (7 downto 0);
  btn_rd: in std_logic;
  btn_wr: in std_logic;
  clk: in std_logic;
  rst: in std_logic;
  sseg: out std_logic_vector (6 downto 0);
  an: out std_logic_vector(3 downto 0);
  empty: out std_logic;
  full: out std_logic );
end main_struct;

architecture Behavioral of main_struct is

component Debouncer is
    Port (
        clk : in STD_LOGIC;
        button : in STD_LOGIC;
        reset : in STD_LOGIC;
        debounced_button : out STD_LOGIC
    );
end component;

component Control_and_fifo is
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
end component;

component display_7seg is
    Port ( digit0 : in STD_LOGIC_VECTOR (3 downto 0);
           digit1 : in STD_LOGIC_VECTOR (3 downto 0);
           digit2 : in STD_LOGIC_VECTOR (3 downto 0);
           digit3 : in STD_LOGIC_VECTOR (3 downto 0);
           clk : in STD_LOGIC;
           cat : out STD_LOGIC_VECTOR (6 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0));
end component;

signal rd: std_logic;
signal wr: std_logic;
signal data_out: std_logic_vector(7 downto 0);
signal emptyCpy: std_logic;
signal fullCpy: std_logic;

begin

p1: debouncer port map(
    clk=>clk,
    button=>btn_rd,
    reset=>rst,
    debounced_button=> rd    
);
p2: debouncer port map(
    clk=>clk,
    button=>btn_wr,
    reset=>rst,
    debounced_button=> wr    
);
p3: Control_and_fifo port map(
    rd=>rd,
    wr=>wr,
    clk=>clk,
    rst=>rst,
    data_in=>data_in,
    empty=>empty,
    full=>full,
    data_out=>data_out
);

p4: display_7seg port map (
    digit0=>data_out(3 downto 0),
    digit1=>data_out(7 downto 4),
    digit2=>data_in(3 downto 0),
    digit3=>data_in(7 downto 4),
    clk=>clk
);
end Behavioral;
