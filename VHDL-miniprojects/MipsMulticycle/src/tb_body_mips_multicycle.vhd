library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity tb_body_mips_multicycle is
--  Port ( );
end tb_body_mips_multicycle;

architecture Behavioral of tb_body_mips_multicycle is

component mainBody is
 Port (
     instruction: in std_logic_vector(31 downto 0);
     clk: in std_logic;
     aluOut: out std_logic_vector (7 downto 0)
  );
end component;

type rom_type is array (0 to 255) of std_logic_vector(31 downto 0);

  signal rom : rom_type := (
     x"00011820", -- 0) $3 <- $0 + $1
     x"00402022", -- 1) $4<- $2 - $0
     x"00212824", -- 2) $5 <- $1 & $1
     x"00233025", -- 3) $6 <- $1 | $3
     x"8C670000", -- 4) $7 <- MEM[$3 + signExt(imm)]
     x"AC010000", -- 5) MEM[$0 + signExt(imm)] <- $1
     others => x"00000000"
   );
   
constant T : time := 20 ns;

signal clk, rst : STD_LOGIC := '0';
signal index : integer := 0;
signal fiveClkCycles: integer := 1;

signal current_instr : STD_LOGIC_VECTOR (31 downto 0) := (others => '0');
signal aluOut : STD_LOGIC_VECTOR (7 downto 0) := (others => '0');

begin
   -- rst <= '1', '0' after 6 * T;
    clk <= not clk after T / 2;

    process (clk)
    begin
        if rising_edge(clk) then
            if rst = '1' then
               index <= 0;
            else
                if fiveClkCycles < 5 then
                    fiveClkCycles <= fiveClkCycles + 1;
                else
                    if index < 6 then -- 6 instructions to test
                        if fiveClkCycles = 5 then
                            index <= index + 1;
                            fiveClkCycles <= 1;
                        end if;
                    else
                        index <= 0;
                    end if;
                end if;
            end if;
        end if;
    end process;
    
    current_instr <= rom(index);

    uut: mainBody port map(
    instruction => current_instr,
     clk => clk,
     aluOut => aluOut
    );
end Behavioral;
