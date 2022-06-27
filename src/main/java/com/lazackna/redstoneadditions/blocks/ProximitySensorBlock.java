package com.lazackna.redstoneadditions.blocks;

import com.lazackna.redstoneadditions.blocks.entity.ProximitySensorTileEntity;
import com.lazackna.redstoneadditions.setup.TileEntityInit;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.RedstoneSide;
import net.minecraft.tileentity.DaylightDetectorTileEntity;
import net.minecraft.tileentity.FurnaceTileEntity;
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

public class ProximitySensorBlock extends Block implements ITileEntityProvider{

//    public static final EnumProperty<RedstoneSide> NORTH = BlockStateProperties.NORTH_REDSTONE;
//    public static final EnumProperty<RedstoneSide> EAST = BlockStateProperties.EAST_REDSTONE;
//    public static final EnumProperty<RedstoneSide> SOUTH = BlockStateProperties.SOUTH_REDSTONE;
//    public static final EnumProperty<RedstoneSide> WEST = BlockStateProperties.WEST_REDSTONE;

    //public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public ProximitySensorBlock(Properties p_i48416_1_) {
        super(p_i48416_1_);
        StateContainer.Builder<Block, BlockState> builder = new StateContainer.Builder<>(this);
        this.createBlockStateDefinition(builder);
        //registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
//        registerDefaultState(this.defaultBlockState().setValue(POWERED, false));

    }

//    @Override
//    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
//        return super.getStateForPlacement(p_196258_1_).setValue(FACING,
//                p_196258_1_.getHorizontalDirection().getOpposite());
//    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(POWERED);
    }

    @Override
    public StateContainer<Block, BlockState> getStateDefinition() {
        StateContainer<Block, BlockState> s = super.getStateDefinition();
        return s;

    }

    @Override
    public boolean isSignalSource(BlockState p_149744_1_) {
        return true;
    }

    @Override
    public int getSignal(BlockState p_180656_1_, IBlockReader p_180656_2_, BlockPos p_180656_3_, Direction p_180656_4_) {
        if(!p_180656_1_.getValue(POWERED)) {
            return 0;
        } else {
            return 15;
        }
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
        return new ProximitySensorTileEntity();
    }
}
