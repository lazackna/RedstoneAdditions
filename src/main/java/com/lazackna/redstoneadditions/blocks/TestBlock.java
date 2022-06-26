package com.lazackna.redstoneadditions.blocks;

import com.lazackna.redstoneadditions.blocks.entity.ProximitySensorTileEntity;
import com.lazackna.redstoneadditions.setup.TileEntityInit;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.RedstoneSide;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class TestBlock extends RedstoneDiodeBlock implements ITileEntityProvider{

//    public static final EnumProperty<RedstoneSide> NORTH = BlockStateProperties.NORTH_REDSTONE;
//    public static final EnumProperty<RedstoneSide> EAST = BlockStateProperties.EAST_REDSTONE;
//    public static final EnumProperty<RedstoneSide> SOUTH = BlockStateProperties.SOUTH_REDSTONE;
//    public static final EnumProperty<RedstoneSide> WEST = BlockStateProperties.WEST_REDSTONE;

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public TestBlock(Properties p_i48416_1_) {
        super(p_i48416_1_);
        StateContainer.Builder<Block, BlockState> builder = new StateContainer.Builder<>(this);
        this.createBlockStateDefinition(builder);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
        registerDefaultState(this.defaultBlockState().setValue(POWERED, false));

    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return super.getStateForPlacement(p_196258_1_).setValue(FACING,
                p_196258_1_.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING, POWERED);
    }

    @Override
    public StateContainer<Block, BlockState> getStateDefinition() {
        StateContainer<Block, BlockState> s = super.getStateDefinition();
        return s;

    }
    AxisAlignedBB bb = AxisAlignedBB.ofSize(30,30,30);

    @Override
    public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
        AxisAlignedBB bb = AxisAlignedBB.ofSize(30,30,30);
        List<LivingEntity> entities =  p_225542_2_.getEntitiesOfClass(LivingEntity.class, bb);

        if(entities.size() > -1) {
            p_225542_2_.setBlock(p_225542_3_, p_225542_1_.setValue(POWERED, true), 2);
        } else {
            p_225542_2_.setBlock(p_225542_3_, p_225542_1_.setValue(POWERED, false), 2);
        }
    }

    @Override
    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        super.tick(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
        AxisAlignedBB bb = AxisAlignedBB.ofSize(30,30,30);
        List<LivingEntity> entities =  p_225534_2_.getEntitiesOfClass(LivingEntity.class, bb);

        if(entities.size() > -1) {
            p_225534_2_.setBlock(p_225534_3_, p_225534_1_.setValue(POWERED, true), 2);
        } else {
            p_225534_2_.setBlock(p_225534_3_, p_225534_1_.setValue(POWERED, false), 2);
        }

    }

    @Override
    protected int getDelay(BlockState p_196346_1_) {
        return 0;
    }


    @Override
    protected int getOutputSignal(IBlockReader p_176408_1_, BlockPos p_176408_2_, BlockState p_176408_3_) {
        return 15;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.PROXIMITY_SENSOR.get().create();
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return null;
    }
}
