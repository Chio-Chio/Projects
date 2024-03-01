----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/19/2023 06:27:02 PM
-- Design Name: 
-- Module Name: tb - Behavioral
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

entity tb is
--  Port ( );
end tb;

architecture Behavioral of tb is
component body_cache is
  Port (
  given_address: in std_logic_vector (15 downto 0);
  --given_word: in std_logic_vector(7 downto 0);
  request_kind: in std_logic; -- 0 for read and 1 for write
  clk_cache: in std_logic;
  result: out std_logic_vector (7 downto 0)
--  write_to_address: out std_logic_vector (5 downto 0);
--  write_data_to_address: out std_logic_vector (7 downto 0)
  );
end component;

-- move rom, intr_type, and data to tb
-- rom has the addresses requested by cpu with rd_wr as the type of instruction
--  and data is what to write at that address in case of wr operation    
type rom_type is array (0 to 63) of std_logic_vector(15 downto 0);
    signal rom: rom_type := (
    --   |tag      | index|offset   where tab = 8biti, index = 6b, offset = 2b
        b"0000_0000_0000_0001", -- add to line 0 
        b"0000_0000_0000_0001", -- add to line 0 
        b"0000_0000_0001_0001", -- add to line 4 
        b"0000_0001_0000_0100", -- add to line 1 
        b"0000_0010_0000_1011", -- add to line 2 and load from data addresse 520, 521 , 522, 523     
        b"0000_0001_0000_1100", -- add to line 3 and load                    268 269 270 271
        others => x"0000"
);
-- add get the block start by dividin by 4 and substract from address the reminder

type instruction_type is array (0 to 63) of std_logic;
    signal rd_wr : instruction_type :=(
        '0',
        '1',
        others => '0'
    ); -- 0 for read and 1 for write
    
type data_type is array (0 to 63) of std_logic_vector(7 downto 0);
   signal data : data_type :=(
        x"00",
        
        others => x"00"
   ); 
--end of move to tb

constant T : time := 20 ns;
signal clk: STD_LOGIC := '0';
signal period: integer := -1;
signal index : integer := 0;
signal current_given_address: std_logic_vector (15 downto 0);
signal current_given_word: std_logic_vector (7 downto 0);
signal current_request_kind: std_logic;
signal current_result: std_logic_vector (7 downto 0);

--given_word: in std_logic_vector(7 downto 0);
--  request_kind: in std_logic; -- 0 for read and 1 for write
--  result: out std_logic_vector (7 downto 0)
begin
clk <= not clk after T / 2;

process (clk)
begin
    if rising_edge(clk) then
               
       if period >= 4 then
            period <= 0;
            index <= index + 1;
        end if;
        if period < 4 then
            period <= period + 1;
        end if;
    end if;
end process;

--given_address: in std_logic_vector (15 downto 0);
--  given_word: in std_logic_vector(7 downto 0);
--  request_kind: in std_logic; -- 0 for read and 1 for write
--  result: out std_logic_vector (7 downto 0)
current_given_address <= rom(index);
current_given_word <= data(index);
current_request_kind <= rd_wr(index);

uut: body_cache port map(
    given_address => current_given_address,
   -- given_word => current_given_word,
    request_kind => current_request_kind,
    clk_cache => clk,
    result => current_result
   
);

end Behavioral;
