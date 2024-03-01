library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity main is
  Port (
          clk : in STD_LOGIC;
          btn : in STD_LOGIC_VECTOR (4 downto 0);
          sw : in STD_LOGIC_VECTOR (15 downto 0);
          led : out STD_LOGIC_VECTOR (15 downto 0);
          an : out STD_LOGIC_VECTOR (3 downto 0);
          cat : out STD_LOGIC_VECTOR (6 downto 0)
   );
end main;

architecture Behavioral of main is

component display_7seg is
    Port ( digit0 : in STD_LOGIC_VECTOR (3 downto 0);
           digit1 : in STD_LOGIC_VECTOR (3 downto 0);
           digit2 : in STD_LOGIC_VECTOR (3 downto 0);
           digit3 : in STD_LOGIC_VECTOR (3 downto 0);
           clk : in STD_LOGIC;
           cat : out STD_LOGIC_VECTOR (6 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0));
end component;

component mpg is
Port ( clk : in STD_LOGIC;
       btn : in STD_LOGIC_vector (4 downto 0);   
       enable: out STD_LOGIC_vector (4 downto 0));  
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

component main_struct is
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
end component;

component Debouncer is
    Port (
        clk : in STD_LOGIC;
        button : in STD_LOGIC;
        reset : in STD_LOGIC;
        debounced_button : out STD_LOGIC
    );
end component;

type vector_array is array (0 to 11) of std_logic_vector(7 downto 0);
signal vector_of_vectors : vector_array := (
    "00000000",  -- 00
    "00000001",  -- 01
    "00000010",  -- 02
    "00000011", -- 03
    "00000100",  -- 04
    "00000101",  -- 05
    "00000110",  -- 06
    "00000111",  -- 07
    "00001000",  -- 08
    "00001001", -- 09
    "10100000",  -- a0
    "10110001"  -- b1
);
signal enb: std_logic_vector (4 downto 0);
signal data_in: std_logic_vector (7 downto 0) :="00000000";
signal data_out: std_logic_vector (7 downto 0);
--signal data_out2:std_logic_vector(7 downto 0);
signal counter_val : integer;
signal an2 : STD_LOGIC_VECTOR (3 downto 0);
signal read, write: std_logic;

begin

p0: mpg port map(
    clk => clk, btn=>btn, enable=>enb 
);
--p0: Debouncer port map(
--    clk => clk,
--        button => btn(0),
--        reset => sw(0),
--        debounced_button => read
--);
--p: Debouncer port map(
--    clk => clk,
--        button => btn(1),
--        reset => sw(0),
--        debounced_button => write
--);



p2:display_7seg port map(
    digit0=> data_out(3 downto 0),
    digit1=> data_out(7 downto 4),
    digit2=>data_in(3 downto 0),
    digit3=>data_in(7 downto 4),
    cat=>cat,
    an=>an,
    clk=>clk
);

process (clk) 
    begin
    if rising_edge(clk) then
           if  enb(0) = '1' then
                counter_val <= counter_val + 1;
                data_in <= vector_of_vectors(counter_val);
             
          end if;
--          if enb(4)='1' then
--            data_out<= (others=>'0');
--          end if;
    end if;--clk if
end process;


--p1: main_struct port map(
--    data_in=>data_in,
--    btn_rd=>btn(0),
--    btn_wr=>btn(1),
--    clk=>clk,
--    rst=>btn(4),
--    sseg=>cat,
--    an=>an,
--    empty=>led(0),
--    full=>led(1)
--    );

p3: Control_and_fifo port map(
    rd=>enb(1),
    wr=>enb(0),
    clk=>clk,
    rst=>btn(4),
    data_in=>data_in,
    empty=> led(0),
    full=>led(1),
    data_out=>data_out
);

end Behavioral;
