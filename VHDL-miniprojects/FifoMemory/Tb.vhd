library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity testbench_fifo_memory is
end testbench_fifo_memory;

architecture Tb of testbench_fifo_memory is

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

constant T : time := 20 ns;

signal clk, rst, rd, wr, rdinc, wrinc, empty, full : STD_LOGIC := '0';
signal data_in, data_out : STD_LOGIC_VECTOR (7 downto 0) := (others => '0'); 

begin

    clk <= not clk after T / 2;
    
    rst <= '1', '0' after 3 * T;
    wr <= '1', '0' after 13 * T;
    rd <= '0', '1' after 15 * T;
    
    process(clk)
    begin
        if rising_edge(clk) then
            if rst = '0' then
                data_in <= data_in + 1;
            end if;
        end if;
    end process;
    
    uut : Control_and_fifo port map (
        clk => clk,
        rst => rst,
        rd => rd,
        wr => wr,
        data_in => data_in,
        data_out => data_out,
        empty => empty,
        full => full
    );
    

end Tb;