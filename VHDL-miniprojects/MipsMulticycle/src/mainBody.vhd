library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_arith.all; 
use ieee.std_logic_unsigned.all;


entity mainBody is
 Port (
     instruction: in std_logic_vector(31 downto 0);
     clk: in std_logic;
     aluOut: out std_logic_vector (7 downto 0)
  );
end mainBody;

architecture Behavioral of mainBody is

component control_unit is
  Port (
   instruction: in std_logic_vector(31 downto 0);
   clk: in std_logic;
   rst : in STD_LOGIC;
   MemRead : out STD_LOGIC;
   MemWrite : out STD_LOGIC;
   RegDst : out STD_LOGIC;
   RegWrite : out STD_LOGIC;
   AluSrcA : out STD_LOGIC;
   AluSrcB : out STD_LOGIC_VECTOR (1 downto 0);
   MemtoReg : out STD_LOGIC;
   IRWrite : out STD_LOGIC;
   PCWrite : out STD_LOGIC;
   AluOp : out STD_LOGIC_VECTOR (1 downto 0)
   );
end component; 

signal pc: std_logic_vector (31 downto 0);
signal rst :  STD_LOGIC;
signal MemRead :  STD_LOGIC;
signal MemWrite :  STD_LOGIC;
signal RegDst :  STD_LOGIC;
signal RegWrite :  STD_LOGIC;
signal AluSrcA :  STD_LOGIC;
signal AluSrcB :  STD_LOGIC_VECTOR (1 downto 0);
signal MemtoReg :  STD_LOGIC;
signal IRWrite :  STD_LOGIC;
signal PCWrite :  STD_LOGIC;
signal AluOp :  STD_LOGIC_VECTOR (1 downto 0);

signal s_aluOut: std_logic_vector(7 downto 0);

signal rs, rt, rd: std_logic_vector (4 downto 0);
signal signExtended: std_logic_vector (31 downto 0);
signal a1: std_logic_vector (15 downto 0);

-- file register should have 31 registers (lines) 
type reg_file_type is array (0 to 31) of std_logic_vector(7 downto 0);
    signal reg_file : reg_file_type := (
        x"00", --  value from $0
        x"01", -- value from $1
        x"02", -- value from $2
        x"03", 
        x"04",
        x"05", 
        x"06", 
        x"07", 
        x"08", 
        x"09",
        x"0A", 
        x"0B", x"0C", x"0D", x"0E",
        others => x"FF"
    );
-- memory  should be bigger than reg file    
type memoryType is array (0 to 128) of std_logic_vector(7 downto 0);
    signal mem : memoryType:= (
        others => x"00"
    );
    
begin

a1 <= instruction(15 downto 0);
-- sign extend
process (a1)
begin
if a1(15) = '1' then
    signExtended <= "1111111111111111"&a1;
else
    signExtended <= "0000000000000000"&a1;
end if;
end process;


controlUnit: control_unit port map(
   instruction => instruction,
   clk => clk,
   rst => rst,
   MemRead =>MemRead,
   MemWrite => MemWrite,
   RegDst => RegDst, 
   RegWrite => RegWrite,
   AluSrcA=> AluSrcA,
   AluSrcB => AluSrcB ,
   MemtoReg => MemtoReg,
   IRWrite => IRWrite ,
   PCWrite => PCWrite,
   AluOp => AluOp
);


process (pcwrite, AluSrcB)
begin
    if pcWrite = '1' and aluSrcB = "00" then
        pc <= pc + 1;
    end if;
end process;

process (irWrite)
begin
    if irWrite = '1' then
        rs <= instruction(25 downto 21);
        rt <= instruction(20 downto 16);
        rd <= instruction(15 downto 11);
    end if;
end process;

--alu
process (aluSrcA, aluSrcB, aluOp)
begin
--    if aluSrcA = '0' and aluSrcB = "00" then
--        s_aluOut <= pc + 1;-- 1 to iterate through addresses_table -- 4;
--    end if;
    if aluSrcB = "01" then
        case aluOp is
            when "00" =>              
                -- simple add
                reg_file(conv_integer(rd)) <= reg_file(conv_integer(rs)) + reg_file(conv_integer(rt));
                aluOut <= reg_file(conv_integer(rd));
            when "01" =>
                --sub
                reg_file(conv_integer(rd)) <= reg_file(conv_integer(rs)) - reg_file(conv_integer(rt));
                aluOut <=reg_file(conv_integer(rd));
            when "10" =>
                -- and
                reg_file(conv_integer(rd)) <= reg_file(conv_integer(rs)) and reg_file(conv_integer(rt));
                aluOut <=reg_file(conv_integer(rd));
            when "11" =>
                -- or
               reg_file(conv_integer(rd)) <= reg_file(conv_integer(rs)) or reg_file(conv_integer(rt));
               aluOut <=reg_file(conv_integer(rd));
            when others =>
                aluOut <= (others => 'Z'); -- invalid instruction
        end case;
    end if;
    
    if aluop ="00" then
        if memRead = '1' then
            -- load
            --$7 <- MEM[$3 + sign_extend (immideate)]
            reg_file(conv_integer(rt)) <= mem(conv_integer(rs) + conv_integer(signExtended));
            aluOut <= reg_file(conv_integer(rt));
        elsif memWrite = '1' then
            -- store
            -- MEM[$0 + sign_extend (immidiate)] <- $1
            mem(conv_integer(rs) + conv_integer(signExtended)) <=  reg_file(conv_integer(rt)); 
            aluOut <=reg_file(conv_integer(rs));           
        end if;
    end if;
    
    if MemtoReg = '1' then
        -- load
        --$7 <- MEM[$3 + sign_extend (immideate)]
        reg_file(conv_integer(rt)) <= mem(conv_integer(rs) + conv_integer(signExtended));    
    end if;
end process;

end Behavioral;
