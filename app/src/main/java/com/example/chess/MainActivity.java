package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton board[][] = new ImageButton[8][8];
    int whiteCheckmate[][] = new int[8][8]; //checks for safety of white pieces can capture
    int blackCheckmate[][] = new int[8][8]; //checks for where the black pieces can capture
    Boolean clickFirst = true, moveWorB = true, stalemateChecking = false;
    ImageButton piece, moveTo, promoteBishop, promoteKnight, promoteRook, promoteQueen;
    TextView header, pawnPromote;
    Button reset;
    int whiteKingCoords[] = new int[2];
    int blackKingCoords[] = new int[2];
    List <int[]> blackPiecesThreatening = new ArrayList<int[]>();
    List <int[]> whitePiecesThreatening = new ArrayList<int[]>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whiteKingCoords[0] = 4;
        whiteKingCoords[1] = 0;
        blackKingCoords[0] = 4;
        blackKingCoords[1] = 7;

        reset = (Button)findViewById(R.id.reset);

        promoteBishop = (ImageButton)findViewById(R.id.bishopPromote);
        promoteKnight = (ImageButton)findViewById(R.id.knightPromote);
        promoteRook = (ImageButton)findViewById(R.id.rookPromote);
        promoteQueen = (ImageButton)findViewById(R.id.queenPromote);

        pawnPromote = (TextView)findViewById(R.id.pawnPromote);

        header = (TextView)findViewById(R.id.header);
        board[0][0]=(ImageButton)findViewById(R.id.a1);
        board[0][1]=(ImageButton)findViewById(R.id.a2);
        board[0][2]=(ImageButton)findViewById(R.id.a3);
        board[0][3]=(ImageButton)findViewById(R.id.a4);
        board[0][4]=(ImageButton)findViewById(R.id.a5);
        board[0][5]=(ImageButton)findViewById(R.id.a6);
        board[0][6]=(ImageButton)findViewById(R.id.a7);
        board[0][7]=(ImageButton)findViewById(R.id.a8);

        board[1][0]=(ImageButton)findViewById(R.id.b1);
        board[1][1]=(ImageButton)findViewById(R.id.b2);
        board[1][2]=(ImageButton)findViewById(R.id.b3);
        board[1][3]=(ImageButton)findViewById(R.id.b4);
        board[1][4]=(ImageButton)findViewById(R.id.b5);
        board[1][5]=(ImageButton)findViewById(R.id.b6);
        board[1][6]=(ImageButton)findViewById(R.id.b7);
        board[1][7]=(ImageButton)findViewById(R.id.b8);

        board[2][0]=(ImageButton)findViewById(R.id.c1);
        board[2][1]=(ImageButton)findViewById(R.id.c2);
        board[2][2]=(ImageButton)findViewById(R.id.c3);
        board[2][3]=(ImageButton)findViewById(R.id.c4);
        board[2][4]=(ImageButton)findViewById(R.id.c5);
        board[2][5]=(ImageButton)findViewById(R.id.c6);
        board[2][6]=(ImageButton)findViewById(R.id.c7);
        board[2][7]=(ImageButton)findViewById(R.id.c8);

        board[3][0]=(ImageButton)findViewById(R.id.d1);
        board[3][1]=(ImageButton)findViewById(R.id.d2);
        board[3][2]=(ImageButton)findViewById(R.id.d3);
        board[3][3]=(ImageButton)findViewById(R.id.d4);
        board[3][4]=(ImageButton)findViewById(R.id.d5);
        board[3][5]=(ImageButton)findViewById(R.id.d6);
        board[3][6]=(ImageButton)findViewById(R.id.d7);
        board[3][7]=(ImageButton)findViewById(R.id.d8);

        board[4][0]=(ImageButton)findViewById(R.id.e1);
        board[4][1]=(ImageButton)findViewById(R.id.e2);
        board[4][2]=(ImageButton)findViewById(R.id.e3);
        board[4][3]=(ImageButton)findViewById(R.id.e4);
        board[4][4]=(ImageButton)findViewById(R.id.e5);
        board[4][5]=(ImageButton)findViewById(R.id.e6);
        board[4][6]=(ImageButton)findViewById(R.id.e7);
        board[4][7]=(ImageButton)findViewById(R.id.e8);

        board[5][0]=(ImageButton)findViewById(R.id.f1);
        board[5][1]=(ImageButton)findViewById(R.id.f2);
        board[5][2]=(ImageButton)findViewById(R.id.f3);
        board[5][3]=(ImageButton)findViewById(R.id.f4);
        board[5][4]=(ImageButton)findViewById(R.id.f5);
        board[5][5]=(ImageButton)findViewById(R.id.f6);
        board[5][6]=(ImageButton)findViewById(R.id.f7);
        board[5][7]=(ImageButton)findViewById(R.id.f8);

        board[6][0]=(ImageButton)findViewById(R.id.g1);
        board[6][1]=(ImageButton)findViewById(R.id.g2);
        board[6][2]=(ImageButton)findViewById(R.id.g3);
        board[6][3]=(ImageButton)findViewById(R.id.g4);
        board[6][4]=(ImageButton)findViewById(R.id.g5);
        board[6][5]=(ImageButton)findViewById(R.id.g6);
        board[6][6]=(ImageButton)findViewById(R.id.g7);
        board[6][7]=(ImageButton)findViewById(R.id.g8);

        board[7][0]=(ImageButton)findViewById(R.id.h1);
        board[7][1]=(ImageButton)findViewById(R.id.h2);
        board[7][2]=(ImageButton)findViewById(R.id.h3);
        board[7][3]=(ImageButton)findViewById(R.id.h4);
        board[7][4]=(ImageButton)findViewById(R.id.h5);
        board[7][5]=(ImageButton)findViewById(R.id.h6);
        board[7][6]=(ImageButton)findViewById(R.id.h7);
        board[7][7]=(ImageButton)findViewById(R.id.h8);

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                whiteCheckmate[i][j] = 0;
                blackCheckmate[i][j] = 0;
            }
        }
    }
    public void reset(View v){

        moveWorB = true;
        header.setText("White to move");
        clickFirst = true;
        blackPiecesThreatening.clear();
        whitePiecesThreatening.clear();
        whiteKingCoords[0] = 4;
        whiteKingCoords[1] = 0;
        blackKingCoords[0] = 4;
        blackKingCoords[1] = 7;

        for (int i = 0; i < 8; i++){
            board[i][1].setTag("pawn_w");
            board[i][1].setImageResource(R.drawable.pawn_w);
            board[i][2].setTag(null);
            board[i][2].setImageResource(R.drawable.neutralboard);
            board[i][3].setTag(null);
            board[i][3].setImageResource(R.drawable.neutralboard);
            board[i][4].setTag(null);
            board[i][4].setImageResource(R.drawable.neutralboard);
            board[i][5].setTag(null);
            board[i][5].setImageResource(R.drawable.neutralboard);
            board[i][6].setTag("pawn_b");
            board[i][6].setImageResource(R.drawable.pawn_b);
        }
        board[0][0].setTag("rook_w");
        board[0][0].setImageResource(R.drawable.rook_w);
        board[1][0].setTag("knight_w");
        board[1][0].setImageResource(R.drawable.knight_w);
        board[2][0].setTag("bishop_w");
        board[2][0].setImageResource(R.drawable.bishop_w);
        board[3][0].setTag("queen_w");
        board[3][0].setImageResource(R.drawable.queen_w);
        board[4][0].setTag("king_w");
        board[4][0].setImageResource(R.drawable.king_w);
        board[5][0].setTag("bishop_w");
        board[5][0].setImageResource(R.drawable.bishop_w);
        board[6][0].setTag("knight_w");
        board[6][0].setImageResource(R.drawable.knight_w);
        board[7][0].setTag("rook_w");
        board[7][0].setImageResource(R.drawable.rook_w);

        board[0][7].setTag("rook_b");
        board[0][7].setImageResource(R.drawable.rook_b);
        board[1][7].setTag("knight_b");
        board[1][7].setImageResource(R.drawable.knight_b);
        board[2][7].setTag("bishop_b");
        board[2][7].setImageResource(R.drawable.bishop_b);
        board[3][7].setTag("queen_b");
        board[3][7].setImageResource(R.drawable.queen_b);
        board[4][7].setTag("king_b");
        board[4][7].setImageResource(R.drawable.king_b);
        board[5][7].setTag("bishop_b");
        board[5][7].setImageResource(R.drawable.bishop_b);
        board[6][7].setTag("knight_b");
        board[6][7].setImageResource(R.drawable.knight_b);
        board[7][7].setTag("rook_b");
        board[7][7].setImageResource(R.drawable.rook_b);


    }
    public void onClick(View v){
        int buttonId = v.getId();
        ImageButton B = (ImageButton)findViewById(buttonId);

        if (clickFirst){
            piece = B;
            if(piece.getTag() != null) {
                if(moveWorB && (piece.getTag().toString().contains("_w"))){
                    clickFirst = false;
                    colors();
                }
                else if (!moveWorB && (piece.getTag().toString().contains("_b"))){
                    clickFirst = false;
                    colors();
                }
            }
        }
        else {
            moveTo = B;
            clickFirst = true;
            stalemateChecking = false;

            if (moveWorB) { //white move
                if (piece.getTag().equals("pawn_w") || piece.getTag().equals("pawn_*_w")) {
                    pawn_w();
                }
                else if (piece.getTag().equals("rook_w") || piece.getTag().equals("rook_*_w")) {
                    rook_w();

                }
                else if (piece.getTag().equals("knight_w")) {
                    knight_w();
                }
                else if (piece.getTag().equals("bishop_w")) {
                    bishop_w();
                }
                else if (piece.getTag().equals("queen_w")) {
                    queen_w();

                }
                else if (piece.getTag().equals("king_w") || piece.getTag().equals("king_*_w")) {
                    king_w();
                }
            }
            else {
                if (piece.getTag().equals("pawn_b") || piece.getTag().equals("pawn_*_b") ) {
                    pawn_b();
                }
                else if (piece.getTag().equals("rook_b") || piece.getTag().equals("rook_*_b")) {
                    rook_b();
                }
                else if (piece.getTag().equals("knight_b")) {
                    knight_b();
                }
                else if (piece.getTag().equals("bishop_b")) {
                    bishop_b();
                }
                else if (piece.getTag().equals("queen_b")) {
                    queen_b();
                }
                else if (piece.getTag().equals("king_b") || piece.getTag().equals("king_*_b")) {
                    king_b();
                }

            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i+j) % 2 == 0) {
                        board[i][j].setBackgroundColor(getResources().getColor(R.color.grayBackground));
                    }
                    else{
                        board[i][j].setBackgroundColor(getResources().getColor(R.color.whiteBackground));
                    }
                }
            }
        }
    }

    public int[] getLocations (){
        int locations[] = new int[4];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piece.getId() == board[i][j].getId()) {
                    locations[0] = i; //x / rows
                    locations[1] = j; //y / columns
                }
                if (moveTo.getId() == board[i][j].getId()) {
                    locations[2] = i; //x / rows
                    locations[3] = j; //y / columns
                }
            }
        }
        return locations;
    }
    public boolean knight_check(int locations[]){

        if((locations[1] == locations[3] - 2 && locations[0] == locations[2] + 1)||(locations[1] == locations[3] - 2 && locations[0] == locations[2] - 1)
                ||(locations[1] == locations[3] + 2 && locations[0] == locations[2] - 1)||(locations[1] == locations[3] + 2 && locations[0] == locations[2] + 1)
                ||(locations[0] == locations[2] - 2 && locations[1] == locations[3] - 1)||(locations[0] == locations[2] - 2 && locations[1] == locations[3] + 1)
                ||(locations[0] == locations[2] + 2 && locations[1] == locations[3] - 1)|| (locations[0] == locations[2] + 2 && locations[1] == locations[3] + 1)){

            return true;
        }

        return false;
    }
    public boolean clear(int locations[], String direction){ //checks if there are no pieces in the way of a move



        int range = 0;
        int range2 = 0;
        int firstcheck = 0;
        int secondcheck = 0;

        if(direction.equals("vertical") && locations[0]==locations[2]){
            if (locations[1] - locations[3] > 0){
                range = locations[1] - locations[3];
                firstcheck = locations[3];
            }
            else {
                range = locations[3] - locations[1];
                firstcheck = locations[1];
            }
            for (int i = firstcheck+1; i < range+firstcheck; i++){
                if(board[locations[0]][i].getTag() != null){
                    return false;
                }
            }
            return true;
        }
        else if(direction.equals("horizontal")&&locations[1]==locations[3]) {
            if (locations[0] - locations[2] > 0){
                range = locations[0] - locations[2];
                firstcheck = locations[2];
            }
            else {
                range = locations[2] - locations[0];
                firstcheck = locations[0];
            }
            for (int i = firstcheck+1; i < range+firstcheck; i++){
                if(board[i][locations[1]].getTag() != null){
                    return false;
                }
            }
            return true;
        }
        else if(direction.equals("diagonal")){
            //vertical difference
            if (locations[1] - locations[3] > 0){
                range = locations[1] - locations[3];
                firstcheck = locations[3];
            }
            else {
                range = locations[3] - locations[1];
                firstcheck = locations[1];
            }
            //horizontal difference
            if (locations[0] - locations[2] > 0){
                range2 = locations[0] - locations[2];
                secondcheck = locations[2];
            }
            else {
                range2 = locations[2] - locations[0];
                secondcheck = locations[0];
            }
            int j = secondcheck+1;
            //if the ranges aren't equal
            if(range!=range2){
                return false;
            }
            //check all the spaces in the given ranges
            if((locations[0]>locations[2]&&locations[1]>locations[3]) || (locations[0]<locations[2]&&locations[1]<locations[3])) {
                for (int i = firstcheck + 1; i < range + firstcheck; i++) {
                    if (board[j][i].getTag() != null) {
                        return false;
                    }
                    j++;

                }
            }
            else{
                j = secondcheck + range - 1;
                for (int i = firstcheck + 1; i < range + firstcheck; i++){
                    if (board[j][i].getTag() != null){
                        return false;
                    }
                    j--;
                }
            }
            return true;
        }
        return false;
    }
    public boolean king_check(int locations[]){ //checks for all legal king's moves
        if(locations[0] == locations[2] && (locations[1]==locations[3]+1 || locations[1]==locations[3]-1)){
            return true;
        }
        else if(locations[1] == locations[3] && (locations[0]==locations[2]+1 || locations[0]==locations[2]-1)){
            return true;
        }
        else if(locations[0] == locations[2]+1 && (locations[1]==locations[3]+1 || locations[1]==locations[3]-1)){
            return true;
        }
        else if(locations[0] == locations[2]-1 && (locations[1]==locations[3]+1 || locations[1]==locations[3]-1)){
            return true;
        }
        return false;
    }
    //TODO: en passant rule
    public void pawn_w (){
        int locations[] = getLocations();
        if(moveTo.getTag() == null && piece.getTag().equals("pawn_w") && locations[0] == locations[2] && locations[3] == locations[1] + 2 && board[locations[0]][locations[1]+1].getTag()==null){ //forward move 2
            moveTo.setImageResource(R.drawable.pawn_w);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag("pawn_*_w");
            piece.setTag(null);
            moveWorB = false;
            header.setText("Black to move");
        }
        else if (moveTo.getTag() == null && locations[0] == locations[2] && locations[3] == locations[1] + 1){ //forward move
            moveTo.setImageResource(R.drawable.pawn_w);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = false;
            header.setText("Black to move");
            if(moveTo.getTag().equals("piece_w")){
                moveTo.setTag("piece_*_w");
            }
            if(locations[3]==7){//pawn promotion
                promoteKnight.setVisibility(View.VISIBLE);
                promoteBishop.setVisibility(View.VISIBLE);
                promoteQueen.setVisibility(View.VISIBLE);
                promoteRook.setVisibility(View.VISIBLE);
                pawnPromote.setVisibility(View.VISIBLE);
            }
        }
        else if (moveTo.getTag() != null && (locations[0] == locations[2] + 1 || locations[0] == locations[2] - 1) && locations[3] == locations[1] + 1 && moveTo.getTag().toString().contains("_b")){ //capture
            moveTo.setImageResource(R.drawable.pawn_w);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = false;
            header.setText("Black to move");
            if(moveTo.getTag().equals("pawn_w")){
                moveTo.setTag("pawn_*_w");
            }
            if(locations[3]==7){//pawn promotion
                promoteKnight.setVisibility(View.VISIBLE);
                promoteBishop.setVisibility(View.VISIBLE);
                promoteQueen.setVisibility(View.VISIBLE);
                promoteRook.setVisibility(View.VISIBLE);
                pawnPromote.setVisibility(View.VISIBLE);
            }
        }

        blackCheckmate = checkmate(blackCheckmate, "_b");
        whiteCheckmate = checkmate(whiteCheckmate, "_w");


    }
    public void pawn_b () {
        int locations[] = getLocations();

        if(moveTo.getTag() == null && piece.getTag().equals("pawn_b") && locations[0] == locations[2] && locations[3] == locations[1] - 2 && board[locations[0]][locations[1]-1].getTag()==null){ //forward move 2

            moveTo.setImageResource(R.drawable.pawn_b);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag("pawn_*_b");
            piece.setTag(null);
            moveWorB = true;
            header.setText("White to move");
        }
        else if (moveTo.getTag() == null && locations[0] == locations[2] && locations[3] == locations[1] - 1) { //forward move

            moveTo.setImageResource(R.drawable.pawn_b);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = true;
            header.setText("White to move");
            if(moveTo.getTag().equals("pawn_b")){
                moveTo.setTag("pawn_*_b");
            }
            if(locations[3]==0){//pawn promotion
                promoteKnight.setVisibility(View.VISIBLE);
                promoteBishop.setVisibility(View.VISIBLE);
                promoteQueen.setVisibility(View.VISIBLE);
                promoteRook.setVisibility(View.VISIBLE);
                pawnPromote.setVisibility(View.VISIBLE);
            }
        }
        else if (moveTo.getTag() != null && (locations[0] == locations[2] + 1 || locations[0] == locations[2] - 1) && locations[3] == locations[1] - 1 && moveTo.getTag().toString().contains("_w")) { //capture

            moveTo.setImageResource(R.drawable.pawn_b);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = true;
            header.setText("White to move");
            if(moveTo.getTag().equals("pawn_b")){
                moveTo.setTag("pawn_*_b");
            }
            if(locations[3]==0){//pawn promotion
                promoteKnight.setVisibility(View.VISIBLE);
                promoteBishop.setVisibility(View.VISIBLE);
                promoteQueen.setVisibility(View.VISIBLE);
                promoteRook.setVisibility(View.VISIBLE);
                pawnPromote.setVisibility(View.VISIBLE);
            }
        }

        whiteCheckmate = checkmate(whiteCheckmate, "_w");
        blackCheckmate = checkmate(blackCheckmate, "_b");
    }
    public void knight_w (){
        int locations[] = getLocations();

        if(knight_check(locations) && (moveTo.getTag() == null || moveTo.getTag().toString().contains("_b"))) {

            moveTo.setImageResource(R.drawable.knight_w);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = false;
            header.setText("Black to move");
        }

        blackCheckmate = checkmate(blackCheckmate, "_b");
        whiteCheckmate = checkmate(whiteCheckmate, "_w");

    }
    public void knight_b (){
        int locations[] = getLocations();

        if(knight_check(locations) && (moveTo.getTag() == null || moveTo.getTag().toString().contains("_w"))) {

            moveTo.setImageResource(R.drawable.knight_b);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = true;
            header.setText("White to move");
        }

        whiteCheckmate = checkmate(whiteCheckmate, "_w");
        blackCheckmate = checkmate(blackCheckmate, "_b");
    }
    public void rook_w(){

        int locations[] = getLocations();

        if((moveTo.getTag()==null || moveTo.getTag().toString().contains("_b")) && (clear(locations, "vertical")||clear(locations,"horizontal"))){

            moveTo.setImageResource(R.drawable.rook_w);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = false;
            header.setText("Black to move");
            if(moveTo.getTag().equals("rook_w")){
                moveTo.setTag("rook_*_w");
            }
        }

        blackCheckmate = checkmate(blackCheckmate, "_b");
        whiteCheckmate = checkmate(whiteCheckmate, "_w");
    }
    public void rook_b(){
        int locations[] = getLocations();

        if((moveTo.getTag()==null || moveTo.getTag().toString().contains("_w")) && (clear(locations, "vertical")||clear(locations,"horizontal"))){
            moveTo.setImageResource(R.drawable.rook_b);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = true;
            header.setText("White to move");
            if(moveTo.getTag().equals("rook_b")){
                moveTo.setTag("rook_*_b");
            }
        }

        whiteCheckmate = checkmate(whiteCheckmate, "_w");
        blackCheckmate = checkmate(blackCheckmate, "_b" );
    }
    public void bishop_w(){
        int locations[] = getLocations();

        if((moveTo.getTag()==null || moveTo.getTag().toString().contains("_b")) && clear(locations,"diagonal")){
            moveTo.setImageResource(R.drawable.bishop_w);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = false;
            header.setText("Black to move");
        }

        blackCheckmate = checkmate(blackCheckmate, "_b");
        whiteCheckmate = checkmate(whiteCheckmate, "_w");
    }
    public void bishop_b(){
        int locations[] = getLocations();

        if((moveTo.getTag()==null || moveTo.getTag().toString().contains("_w")) && clear(locations,"diagonal")){
            moveTo.setImageResource(R.drawable.bishop_b);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = true;
            header.setText("White to move");
        }

        whiteCheckmate = checkmate(whiteCheckmate, "_w");
        blackCheckmate = checkmate(blackCheckmate, "_b" );
    }
    public void queen_w(){
        int locations[] = getLocations();
        if((moveTo.getTag()==null || moveTo.getTag().toString().contains("_b")) && (clear(locations,"horizontal") || clear(locations,"vertical") || clear(locations,"diagonal"))){
            moveTo.setImageResource(R.drawable.queen_w);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = false;
            header.setText("Black to move");
        }

        blackCheckmate = checkmate(blackCheckmate, "_b" );
        whiteCheckmate = checkmate(whiteCheckmate, "_w" );
    }
    public void queen_b(){
        int locations[] = getLocations();
        if((moveTo.getTag()==null || moveTo.getTag().toString().contains("_w")) && (clear(locations, "vertical")||clear(locations,"horizontal")||clear(locations,"diagonal"))){
            moveTo.setImageResource(R.drawable.queen_b);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = true;
            header.setText("White to move");
        }

        whiteCheckmate = checkmate(whiteCheckmate, "_w" );
        blackCheckmate = checkmate(blackCheckmate, "_b" );
    }
    public void king_w(){
        int locations[] = getLocations();

        if((moveTo.getTag()==null || moveTo.getTag().toString().contains("_b")) && king_check(locations)){ // regular moves
            moveTo.setImageResource(R.drawable.king_w);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = false;
            header.setText("Black to move");
            if(moveTo.getTag().equals("king_w")){
                moveTo.setTag("king_*_w");
            }
            whiteKingCoords[0] = locations[2];
            whiteKingCoords[1] = locations[3];
        }
        else if (piece.getTag().toString().contains("_*")==false && moveTo.getTag()==null &&
                ((board[locations[2]+1][locations[3]].getTag()!=null && board[locations[2]+1][locations[3]].getTag().toString().equals("rook_w")==true)
                        || (board[locations[2]-2][locations[3]].getTag()!=null && board[locations[2]-2][locations[3]].getTag().toString().equals("rook_w")==true)) && clear(locations, "horizontal")){
            //Log.d("King", "Entered castling");
            if(locations[0]+2==locations[2]){
                //Log.d("King,", "Entered Locations 0+2");

                board[locations[0]+2][locations[1]].setImageResource(R.drawable.king_w);
                board[locations[0]+2][locations[1]].setTag("king_*_w");
                piece.setImageResource(R.drawable.neutralboard);
                piece.setTag(null);

                board[locations[2]-1][locations[3]].setImageResource(R.drawable.rook_w);
                board[locations[2]-1][locations[3]].setTag("rook_*_w");
                board[locations[2]+1][locations[3]].setImageResource(R.drawable.neutralboard);
                board[locations[2]+1][locations[3]].setTag(null);

                whiteKingCoords[0] = locations[0]+2;
                whiteKingCoords[1] = locations[1];

                moveWorB = false;
                header.setText("Black to move");
            }
            else if(locations[0]-2==locations[2]){
                board[locations[0]-2][locations[1]].setImageResource(R.drawable.king_w);
                board[locations[0]-2][locations[1]].setTag("king_*_w");
                piece.setImageResource(R.drawable.neutralboard);
                piece.setTag(null);
                board[locations[2]+1][locations[3]].setImageResource(R.drawable.rook_w);
                board[locations[2]+1][locations[3]].setTag("rook_*_w");
                board[locations[2]-2][locations[3]].setImageResource(R.drawable.neutralboard);
                board[locations[2]-2][locations[3]].setTag(null);

                whiteKingCoords[0] = locations[0]-2;
                whiteKingCoords[1] = locations[1];

                moveWorB = false;
                header.setText("Black to move");
            }
        }

        blackCheckmate = checkmate(blackCheckmate, "_b");
        whiteCheckmate = checkmate(whiteCheckmate, "_w");
    }
    public void king_b(){
        int locations[] = getLocations();
        if((moveTo.getTag()==null || moveTo.getTag().toString().contains("_w")) && king_check(locations)){ //regular moves
            moveTo.setImageResource(R.drawable.king_b);
            piece.setImageResource(R.drawable.neutralboard);
            moveTo.setTag(piece.getTag());
            piece.setTag(null);
            moveWorB = true;
            header.setText("White to move");
            if(moveTo.getTag().equals("king_b")){
                moveTo.setTag("king_*_b");
            }
            blackKingCoords[0] = locations[2];
            blackKingCoords[1] = locations[3];
        }
        else if (piece.getTag().toString().contains("_*")==false && moveTo.getTag()==null &&
                ((board[locations[2]+1][locations[3]].getTag()!=null && board[locations[2]+1][locations[3]].getTag().toString().equals("rook_b")==true)
                        || (board[locations[2]-2][locations[3]].getTag()!=null && board[locations[2]-2][locations[3]].getTag().toString().equals("rook_b")==true)) && clear(locations, "horizontal")){
            if(locations[0]+2==locations[2]){
                board[locations[0]+2][locations[1]].setImageResource(R.drawable.king_b);
                board[locations[0]+2][locations[1]].setTag("king_*_b");
                piece.setImageResource(R.drawable.neutralboard);
                piece.setTag(null);

                board[locations[2]-1][locations[3]].setImageResource(R.drawable.rook_b);
                board[locations[2]-1][locations[3]].setTag("rook_*_b");
                board[locations[2]+1][locations[3]].setImageResource(R.drawable.neutralboard);
                board[locations[2]+1][locations[3]].setTag(null);

                blackKingCoords[0] = locations[0]+2;
                blackKingCoords[1] = locations[1];

                moveWorB = true;
                header.setText("White to move");
            }
            if(locations[0]-2==locations[2]){
                board[locations[0]-2][locations[1]].setImageResource(R.drawable.king_b);
                board[locations[0]-2][locations[1]].setTag("king_*_b");
                piece.setImageResource(R.drawable.neutralboard);
                piece.setTag(null);
                board[locations[2]+1][locations[3]].setImageResource(R.drawable.rook_b);
                board[locations[2]+1][locations[3]].setTag("rook_*_b");
                board[locations[2]-2][locations[3]].setImageResource(R.drawable.neutralboard);
                board[locations[2]-2][locations[3]].setTag(null);

                blackKingCoords[0] = locations[0]-2;
                blackKingCoords[1] = locations[1];

                moveWorB = true;
                header.setText("White to move");
            }
        }
        whiteCheckmate = checkmate(whiteCheckmate, "_w");
        blackCheckmate = checkmate(blackCheckmate, "_b");
    }
    public void checkmateHelper(int x, int y, int j, int k, int position, String sideColor){
        int[] coordinateHolder = new int[3];
        Log.d("ERROR1","check 1");
        Log.d("ERROR1",String.valueOf(x));
        Log.d("ERROR1",String.valueOf(y));
        if(x>=0 && x<8 && y>=0 && y<8 && board[x][y].getTag()!=null && board[x][y].getTag().toString().contains("king")){
            Log.d("ERROR1","check 2");
            if(sideColor.equals("_w")){
                coordinateHolder[0] = j;
                coordinateHolder[1] = k;
                coordinateHolder[2] = position;
                whitePiecesThreatening.add(coordinateHolder);
            }
            else if(sideColor.equals("_b")){
                coordinateHolder[0] = j;
                coordinateHolder[1] = k;
                coordinateHolder[2] = position;
                blackPiecesThreatening.add(coordinateHolder);
            }

            Log.d("ERROR1 - j", String.valueOf(j));
            Log.d("ERROR1 - k", String.valueOf(k));
            Log.d("ERROR1 - pos", String.valueOf(position));
        }
        Log.d("ERROR1","check 3");
    }

    public int[][] checkmate(int[][] capturingArray, String sideColor){
        //Log.d("ERROR:", "Entered 1");
        //resets board
        int[] coordinateHolder = new int[3];
        /*
        8 1 2
        7 P 3
        6 5 4

          11 12
        18     13
        17  K  14
          16 15
         */
        for(int k = 0; k < 8; k++){
            for(int j = 0; j < 8; j++) {
                capturingArray[j][k] = 0;
            }
        }
        Log.d("ERROR","Checking pre jk for loop");
        //checks every square
        for(int k = 0; k < 8; k++){
            for(int j = 0; j < 8; j++){
                Log.d("ERROR","jk loop: k: " + k + " j: " + j);
                if(board[j][k].getTag() != null && board[j][k].getTag().toString().contains(sideColor)) {
                    Log.d("ERROR","Post side color check");
                    if (board[j][k].getTag().toString().equals("pawn_w") || board[j][k].getTag().toString().equals("pawn_*_w")) {//if the piece is a pawn | works
                        Log.d("ERROR","Entered White Pawn Check");
                        if(j+1<8 && k+1<8 && board[j+1][k+1].getTag()!=null && board[j+1][k+1].getTag().toString().contains("_b")) { //up right
                            capturingArray[j + 1][k + 1]++;
                            checkmateHelper(j+1, k+1, j, k, 0, sideColor);
                        }
                        Log.d("ERROR", "check 1");
                        if(j-1>=0 && k+1<8 && board[j-1][k+1].getTag()!=null && board[j-1][k+1].getTag().toString().contains("_b")) { //up left
                            capturingArray[j - 1][k + 1]++;
                            checkmateHelper(j-1, k+1, j, k, 0, sideColor);
                        }
                        Log.d("ERROR", "check 2");
                        if(k-1>=0 && board[j][k-1].getTag()==null) { //forward blocking
                            capturingArray[j][k - 1]++;
                            checkmateHelper(j, k+1, j, k, 1, sideColor);
                        }
                        Log.d("ERROR", "finished white pawn check");
                    }
                    else if(board[j][k].getTag().toString().equals("pawn_b") || board[j][k].getTag().toString().equals("pawn_*_b")) {//if the piece is a pawn | works
                        Log.d("ERROR","Entered Black Pawn Check");
                        if(j+1<8 && k-1>=0 && board[j+1][k-1].getTag()!=null && board[j+1][k-1].getTag().toString().contains("_w")) { //down right
                            capturingArray[j + 1][k - 1]++;
                            checkmateHelper(j+1, k-1, j, k, 0, sideColor);
                            Log.d("ERROR5", "post checkmate helper");
                        }
                        Log.d("ERROR", "check b1");
                        if(j-1>=0 && k-1>=0 && board[j-1][k-1].getTag()!=null && board[j-1][k-1].getTag().toString().contains("_w")) { //down left
                            capturingArray[j - 1][k - 1]++;
                            checkmateHelper(j-1, k-1, j, k, 0, sideColor);
                        }
                        Log.d("ERROR", "check b2");
                        if(k-1>=0 && board[j][k-1].getTag()==null) { //forward blocking
                            capturingArray[j][k - 1]++;
                            checkmateHelper(j, k-1, j, k, 5, sideColor);
                        }
                        Log.d("ERROR", "finished black pawn check");
                    }
                    else if(board[j][k].getTag().toString().contains("knight")){ //if the piece is a knight | works
                        Log.d("ERROR","Entered Knight Check");
                        if(j+2<8 && k+1<8) { //top right
                            capturingArray[j + 2][k + 1]++;
                            checkmateHelper(j+2, k+1, j, k, 0, sideColor);
                        }
                        if(j+2<8 && k-1>=0) { //top left
                            capturingArray[j + 2][k - 1]++;
                            checkmateHelper(j+2, k-1, j, k, 0, sideColor);
                        }

                        if(j-2>=0 && k+1<8) { //bottom right
                            capturingArray[j - 2][k + 1]++;
                            checkmateHelper(j-2, k+1, j, k, 0, sideColor);
                        }

                        if(j-2>=0 && k-1>=0) { //bottom left
                            capturingArray[j - 2][k - 1]++;
                            checkmateHelper(j-2, k-1, j, k, 0, sideColor);
                        }

                        if(j+1<8 && k+2<8) { //right top
                            capturingArray[j + 1][k + 2]++;
                            checkmateHelper(j+1, k+2, j, k, 0, sideColor);
                        }

                        if(j+1<8 && k-2>=0) { //left top
                            capturingArray[j + 1][k - 2]++;
                            checkmateHelper(j+1, k-2, j, k, 0, sideColor);
                        }

                        if(j-1>=0 && k+2<8) { //right bottom
                            capturingArray[j - 1][k + 2]++;
                            checkmateHelper(j-1, k+2, j, k, 0, sideColor);
                        }

                        if(j-1>=0 && k-2>=0) { //left bottom
                            capturingArray[j - 1][k - 2]++;
                            checkmateHelper(j-1, k-2, j, k, 0, sideColor);
                        }

                    }
                    else if(board[j][k].getTag().toString().contains("rook")||board[j][k].getTag().toString().contains("queen")){//if the piece is a rook or the queen straight moves | works with rook and queen
                        Log.d("ERROR1","Entered Rook/Queen Check");
                        int i = 1;
                        while(k+i<8 && board[j][k+i].getTag()==null){
                            Log.d("ERROR1","Entering if 1");
                            Log.d("ERROR1","i: " + i);
                            Log.d("ERROR1","Condition 1");
                            capturingArray[j][k+i]++;
                            i++;
                        }
                        Log.d("ERROR1","Entering if 2");
                        Log.d("ERROR1", String.valueOf(k));
                        Log.d("ERROR1", String.valueOf(j));
                        Log.d("ERROR1", String.valueOf(i));
                        Log.d("ERROR1", sideColor);
                        if(k+i<8 && board[j][k+i].getTag() != null){
                            capturingArray[j][k+i]++;
                            if(!board[j][k+i].getTag().toString().contains(sideColor)){
                                checkmateHelper(j, k + i, j, k, 1, sideColor);
                                if (k + i + 1 < 8 && board[j][k + i].getTag().toString().contains("king")) {
                                    i++;
                                    capturingArray[j][k + i]++;
                                }
                            }
                        }
                        Log.d("ERROR1","Finished Direction 1");
                        i = 1;
                        while(k-i>=0 && k+i<8 && board[j][k-i].getTag()==null){
                            capturingArray[j][k-i]++;
                            i++;
                        }
                        if(k-i>=0 && board[j][k-i].getTag() != null){
                            capturingArray[j][k-i]++;
                            if(!board[j][k-i].getTag().toString().contains(sideColor)){
                                checkmateHelper(j, k - i, j, k, 5, sideColor);
                                if (k - i - 1 >= 0 && board[j][k - i].getTag().toString().contains("king")) {
                                    i++;
                                    capturingArray[j][k - i]++;
                                }
                            }
                        }
                        i = 1;
                        Log.d("ERROR","Finished Direction 2");
                        Log.d("ERROR", String.valueOf(j+i));
                        Log.d("ERROR", String.valueOf(j-i));


                        while(j+i<8 && j-i>=0 && board[j+i][k].getTag()==null){
                            Log.d("ERROR", "b1");
                            capturingArray[j+i][k]++;
                            i++;
                        }
                        Log.d("ERROR", "b4");
                        if(j+i<8 && board[j+i][k].getTag() != null){
                            capturingArray[j+i][k]++;
                            if( !board[j+i][k].getTag().toString().contains(sideColor)) {
                                checkmateHelper(j + i, k, j, k, 7, sideColor);
                                if (j + i + 1 < 8 && board[j + i][k].getTag().toString().contains("king")) {
                                    i++;
                                    capturingArray[j + i][k]++;
                                }
                            }
                        }
                        i = 1;
                        Log.d("ERROR","Finished Direction 3");
                        while(j-i>=0 && board[j-i][k].getTag()==null){
                            capturingArray[j-i][k]++;
                            i++;
                        }
                        if(j-i>=0 && board[j-i][k].getTag() != null){
                            capturingArray[j-i][k]++;
                            if(!board[j-i][k].getTag().toString().contains(sideColor)) {
                                checkmateHelper(j - i, k, j, k, 3, sideColor);
                                if (j - i - 1 >= 0 && board[j - i][k].getTag().toString().contains("king")) {
                                    i++;
                                    capturingArray[j - i][k]++;
                                }
                            }
                        }
                        Log.d("ERROR","Finished Direction 4");
                    }
                    if(board[j][k].getTag().toString().contains("bishop")||board[j][k].getTag().toString().contains("queen")){ //if the piece is a bishop of the queen diagonal moves | works with the bishop and queen
                        Log.d("ERROR","Entered Bishop/Queen Check");
                        int i = 1;
                        while(j+i<8 && k+i<8 && board[j+i][k+i].getTag()==null){
                            capturingArray[j+i][k+i]++;
                            i++;
                        }
                        if(j+i<8 && k+i<8 && board[j+i][k+i].getTag() != null){
                            Log.d("ERROR","Finished Direction e");
                            capturingArray[j+i][k+i]++;
                            if(!!board[j+i][k+i].getTag().toString().contains(sideColor)) {
                                checkmateHelper(j + i, k + i, j, k, 6, sideColor);
                                if (j + i + 1 < 8 && k + i + 1 < 8 && board[j + i][k + i].getTag().toString().contains("king")) {
                                    i++;
                                    capturingArray[j + i][k + i]++;
                                }
                            }
                        }
                        Log.d("ERROR","Finished Direction 1");
                        i = 1;
                        while(j-i>=0 && k+i<8 && board[j-i][k+i].getTag()==null){
                            capturingArray[j-i][k+i]++;
                            i++;
                        }
                        if(j-i>=0 && k+i<8 && board[j-i][k+i].getTag() != null){
                            capturingArray[j-i][k+i]++;
                            if(!board[j-i][k+i].getTag().toString().contains(sideColor)){
                                checkmateHelper(j - i, k + i, j, k, 4, sideColor);
                                if (j - i - 1 >= 0 && k + i + 1 < 8 && board[j - i][k + i].getTag().toString().contains("king")) {
                                    i++;
                                    capturingArray[j - i][k + i]++;
                                }
                            }
                        }
                        Log.d("ERROR","Finished Direction 2");
                        i = 1;
                        Log.d("ERROR","c1");
                        while(j+i<8 && k-i>=0 && board[j+i][k-i].getTag()==null){
                            capturingArray[j+i][k-i]++;
                            i++;
                        }
                        Log.d("ERROR","c5");
                        if(j+i<8 && k-i>=0 && board[j+i][k-i].getTag() != null){
                            Log.d("ERROR","c6");
                            capturingArray[j+i][k-i]++;
                            if(!board[j+i][k-i].getTag().toString().contains(sideColor)){
                                checkmateHelper(j + i, k - i, j, k, 8, sideColor);
                                if (j + i + 1 < 8 && k - i - 1 >= 0 && board[j + i][k - i].getTag().toString().contains("king")) {
                                    i++;
                                    capturingArray[j + i][k - i]++;
                                }
                            }
                        }

                        Log.d("ERROR","Finished Direction 3");
                        i = 1;
                        while(j-i>=0 && k-i>=0 && board[j-i][k-i].getTag()==null){
                            capturingArray[j-i][k-i]++;
                            i++;
                        }
                        if(j-i>=0 && k-i>=0 && board[j-i][k-i].getTag() != null){
                            capturingArray[j-i][k-i]++;
                            if(!board[j-i][k-i].getTag().toString().contains(sideColor)) {
                                checkmateHelper(j - i, k - i, j, k, 2, sideColor);
                                if (j - i - 1 >= 0 && k - i - 1 >= 0 && board[j - i][k - i].getTag().toString().contains("king")) {
                                    i++;
                                    capturingArray[j - i][k - i]++;
                                }
                            }
                        }
                        Log.d("ERROR","Finished Direction 4");
                    }
                    else if(board[j][k].getTag().toString().contains("king")){ //if the piece is the king
                        Log.d("ERROR","Entered King Check");
                        if(j-1>=0 && k-1>=0 && board[j-1][k-1].getTag() != null && !board[j-1][k-1].getTag().toString().contains(sideColor)){
                            capturingArray[j-1][k-1]++;
                        }
                        if(j+1<8 && k-1>=0 && board[j+1][k-1].getTag() != null && !board[j+1][k-1].getTag().toString().contains(sideColor)){
                            capturingArray[j+1][k-1]++;
                        }
                        if(j-1>=0 && k+1<8 && board[j-1][k+1].getTag() != null && !board[j-1][k+1].getTag().toString().contains(sideColor)){
                            capturingArray[j-1][k+1]++;
                        }
                        if(j+1<8 && k+1<8 && board[j+1][k+1].getTag() != null && !board[j+1][k+1].getTag().toString().contains(sideColor)){
                            capturingArray[j+1][k+1]++;
                        }
                        if(k-1>=0 && board[j][k-1].getTag() != null && !board[j][k-1].getTag().toString().contains(sideColor)){
                            capturingArray[j][k-1]++;
                        }
                        if(k+1<8 && board[j][k+1].getTag() != null && !board[j][k+1].getTag().toString().contains(sideColor)){
                            capturingArray[j][k+1]++;
                        }
                        if(j-1>=0 && board[j-1][k].getTag() != null && !board[j-1][k].getTag().toString().contains(sideColor)){
                            capturingArray[j-1][k]++;
                        }
                        if(j+1<8 && board[j+1][k].getTag() != null && !board[j+1][k].getTag().toString().contains(sideColor)){
                            capturingArray[j+1][k]++;
                        }
                    }
                }
            }
        }
        //prints board status
        for (int i = 7; i >=0; i--){
            for (int j = 0; j <8; j++){
                System.out.print(capturingArray[j][i]);
            }
            System.out.println(" ");
        }
        if(stalemateChecking){

        }
        //actual checker
        if(sideColor.equals("_w")){
            Log.d("ERROR","Enter Checkmate Check White");
            checkmateCheck("king_w");
        }
        else if(sideColor.equals("_b")){
            Log.d("ERROR","Entering Checkmate Check Black");
            checkmateCheck( "king_b");
        }
        Log.d("ERROR","Returning capturing array");
        return capturingArray;
    }

    //returns false if okay && true if checkmate is true

    /*
    * Checkmate:
    * 1. The king must first be in check
    * 2. All surrounding squares must be
    *   a. nonexistent
    *   b. filled with a friendly piece
    *   c. filled with an enemy piece
    *       - that is protected by another enemy piece(s)
    *   d. if the king were to move to another square, he would immediately be captured
    *   e. a friendly piece is unable block
    *
    *
    *
    * The king and all surrounding existent squares have to be in check or are a friendly piece
    *  - friendly pieces are unable to block a direct check
    *
    * 3.0
    *
    *
    * Pieces don't affect their own square
    * it is a checkmate if:
    * King cannot only move to a "net" negative number or greater
    * King cannot move to a square with a friendly piece
    * it is not a checkmate if:
    * Blocking can only occur when the king is threatened by ONE piece (with the exception of the knight)
    *   if there's a positive number of 1 on the king's spot
    *   if there's a negative number (Not the sum) between the king and threatening piece
    *       if immediately next to the king, negative -2
    *
    * Capturing can only occur when there is ONE threatening piece and that piece is under threat
    *
    * IF: all surrounding squares are "Net" negatives or greater,
    *     the king is "under the heat" of one un-blockable or multiple pieces
    *
    * THEN: Checkmate
    *
    * Equivalent to stalemate + check
    *
    *
    * STALEMATE:
    * 1. King cannot move anywhere without being captured
    * 2. Other pieces are unable to move
    *
    *
    * */
    public void checkmateCheck(String kingTag){
        Log.d("ERROR","Entered Checkmate Check");
        int[][] checkingArray;
        int[][] opposingCheckingArray;
        int[] kingCoords;
        List <int[]> piecesThreatening;
        int checkmate = 0;//if == 9 then game over
        boolean check = false;
        boolean stalemateConOne = false;
        String sideColor;
        //findPiece(kingTag);

        if(kingTag.equals("king_w")){
            kingCoords = blackKingCoords;
            checkingArray = blackCheckmate;
            opposingCheckingArray = whiteCheckmate;
            //piecesThreatening = blackPiecesThreatening;
            piecesThreatening = whitePiecesThreatening;

            sideColor = "_b";
        }
        else{
            kingCoords = whiteKingCoords;
            checkingArray = whiteCheckmate;
            opposingCheckingArray = blackCheckmate;
            //piecesThreatening = whitePiecesThreatening;
            piecesThreatening = blackPiecesThreatening;

            sideColor = "_w";
        }
        Log.d("ERROR","Finished setting variables in Checkmate Check");
        Log.d("ERROR","Starting basic check");
        //check
        if(!moveWorB && whiteCheckmate[blackKingCoords[0]][blackKingCoords[1]]>0){
            header.setText("Check: Black to move");
            checkmate++;
            Log.d("ERROR","Checkmate 0");
            Log.d("ERROR", String.valueOf(checkmate));
            check = true;
        }
        else if(moveWorB && blackCheckmate[whiteKingCoords[0]][whiteKingCoords[1]]>0){
            header.setText("Check: White to move");
            checkmate++;
            Log.d("ERROR","Checkmate 0");
            Log.d("ERROR", String.valueOf(checkmate));
            check = true;
        }
        //checkmate
        if(checkmate==1) {
            for (int i = kingCoords[0] - 1; i < kingCoords[0] + 2; i++) {
                for (int j = kingCoords[1] - 1; j < kingCoords[1] + 2; j++) {
                    Log.d("ERROR", "CHECKING NEW SQUARE");
                    Log.d("ERROR", "I:");
                    Log.d("ERROR", String.valueOf(i));
                    Log.d("ERROR", "J:");
                    Log.d("ERROR", String.valueOf(j));

                    if (i < 0 || i >= 8 || j < 0 || j >= 8) { // if out of bounds
                        checkmate++;
                        Log.d("ERROR", "Checkmate 1:");
                        Log.d("ERROR", String.valueOf(checkmate));
                    } else if (board[i][j].getTag() != null && !board[i][j].getTag().toString().contains("king")) {
                        if (board[i][j].getTag().toString().contains(sideColor)) {
                            checkmate++;
                            Log.d("ERROR", "Checkmate 2");
                            Log.d("ERROR", String.valueOf(checkmate));
                        } else if (!board[i][j].getTag().toString().contains("_w") && whiteCheckmate[i][j] >= 1) {
                            checkmate++;
                            Log.d("ERROR", "Checkmate 2");
                            Log.d("ERROR", String.valueOf(checkmate));
                        } else if (!board[i][j].getTag().toString().contains("_b") && blackCheckmate[i][j] >= 1) {
                            checkmate++;
                            Log.d("ERROR", "Checkmate 2");
                            Log.d("ERROR", String.valueOf(checkmate));
                        }
                    } else if (board[i][j].getTag() == null && opposingCheckingArray[i][j] > 0) {
                        checkmate++;
                        Log.d("ERROR", "Checkmate 3:");
                        Log.d("ERROR", String.valueOf(checkmate));
                    }

                }
            }
        }

        if(check == false && checkmate==8){
            stalemateConOne = true;
            stalemate(kingCoords, checkingArray, opposingCheckingArray, sideColor);

        }

        //blocking

        /*
        * pawns can be directly captured if they are threatening diagonally
        * knights can be directly captured if they are threatening
        * */
        Log.d("ERROR6", String.valueOf(piecesThreatening.size()));
        if(piecesThreatening.size()==1){
            Log.d("ERROR","Entered Blocking");
            //check straight or diagonal
            int i = 1;
            int coordinates[] = piecesThreatening.get(0);

            Log.d("ERROR", "blocking coordinates");
            Log.d("ERROR",String.valueOf(coordinates[0]));
            Log.d("ERROR",String.valueOf(coordinates[1]));

            for (int f = 7; f >=0; f--){
                for (int j = 0; j <8; j++){
                    System.out.print(checkingArray[j][f]);//Switched opposing to checking
                }
                System.out.println(" ");
            }
            //TODO: force player to move out of check
            if(checkingArray[coordinates[0]][coordinates[1]]>0){ //directly capturing attacking piece
                checkmate--;
                Log.d("ERROR", "directly capturing");
            }
            else {
                Log.d("ERROR","entered else");
                switch (coordinates[2]) {
                    case 1: //top
                        while (board[coordinates[0]][coordinates[1] - i].getTag() != null && !board[coordinates[0]][coordinates[1] - i].getTag().toString().contains("king")) {
                            Log.d("ERROR", "enter case 1");
                            if (opposingCheckingArray[coordinates[0]][coordinates[1] - i] > 0) {//block attacking piece
                                checkmate--;
                                break;
                            }
                            i++;
                        }
                        break;
                    case 2://top right diagonal
                        while (board[coordinates[0] - i][coordinates[1] - i].getTag() != null && !board[coordinates[0] - i][coordinates[1] - i].getTag().toString().contains("king")) {
                            Log.d("ERROR", "enter case 2");
                            if (opposingCheckingArray[coordinates[0] - i][coordinates[1] - i] > 0) {//block attacking piece
                                checkmate--;
                                break;
                            }
                            i++;
                        }
                        break;
                    case 3: //right
                        while (board[coordinates[0] - i][coordinates[1]].getTag() != null && !board[coordinates[0] - i][coordinates[1]].getTag().toString().contains("king")) {
                            Log.d("ERROR", "enter case 3");
                            if (opposingCheckingArray[coordinates[0] - i][coordinates[1]] > 0) {//block attacking piece
                                checkmate--;
                                break;
                            }
                            i++;
                        }
                        break;
                    case 4://bottom right diagonal
                        while (board[coordinates[0] - i][coordinates[1] + i].getTag() != null && !board[coordinates[0] - i][coordinates[1] + i].getTag().toString().contains("king")) {
                            Log.d("ERROR", "enter case 4");
                            if (opposingCheckingArray[coordinates[0] - i][coordinates[1] + i] > 0) {//block attacking piece
                                checkmate--;
                                break;
                            }
                            i++;
                        }
                        break;
                    case 5://bottom
                        while (board[coordinates[0]][coordinates[1] + i].getTag() != null && !board[coordinates[0]][coordinates[1] + i].getTag().toString().contains("king")) {
                            Log.d("ERROR", "enter case 5");
                            if (opposingCheckingArray[coordinates[0]][coordinates[1] + i] > 0) {//block attacking piece
                                checkmate--;
                                break;
                            }
                            i++;
                        }
                        break;
                    case 6://bottom left diagonal
                        while (board[coordinates[0] + i][coordinates[1] + i].getTag() != null && !board[coordinates[0] + i][coordinates[1] + i].getTag().toString().contains("king")) {
                            Log.d("ERROR", "enter case 6");
                            if (opposingCheckingArray[coordinates[0] + i][coordinates[1] + i] > 0) {//block attacking piece
                                checkmate--;
                                break;
                            }
                            i++;
                        }
                        break;
                    case 7://left
                        while (board[coordinates[0] + i][coordinates[1]].getTag() != null && !board[coordinates[0] + i][coordinates[1]].getTag().toString().contains("king")) {
                            Log.d("ERROR", "enter case 7");
                            if (opposingCheckingArray[coordinates[0] + i][coordinates[1]] > 0) { //block attacking piece
                                checkmate--;
                                break;
                            }
                            i++;
                        }
                        break;
                    case 8: //top left diagonal
                        while (board[coordinates[0] + i][coordinates[1] - i].getTag() != null && !board[coordinates[0] + i][coordinates[1] - i].getTag().toString().contains("king")) {
                            Log.d("ERROR", "enter case 8");
                            if (opposingCheckingArray[coordinates[0] + i][coordinates[1] - i] > 0) { //block attacking piece
                                checkmate--;
                                break;
                            }
                            i++;
                        }
                        break;
                }
                Log.d("ERROR","Finished Switch Statement");
            }
        }
        Log.d("ERROR", String.valueOf(checkmate));
        if(checkmate == 9){
            header.setText("Game Over: Checkmate\n");
            Log.d("ERROR", "CHECKMATE OFFICIAL ");
        }

        Log.d("ERROR","Finished checking");
        whitePiecesThreatening.clear();
        blackPiecesThreatening.clear();
    }
    public int[] findPiece (String pieceTag){
        int pieceCoords[] = new int[2];

        Log.d("ERROR","find king");
        //find king
        for(int k = 0; k < 8; k++){
            Log.d("ERROR","k");
            Log.d("ERROR",String.valueOf(k));

            for(int j = 0; j < 8; j++) {
                Log.d("ERROR","j");
                Log.d("ERROR",String.valueOf(j));

                if(board[j][k].getTag().toString().equals(pieceTag)){
                    Log.d("ERROR","king_w");
                    pieceCoords[0] = j;
                    pieceCoords[1] = k;
                    return pieceCoords;
                }
            }
        }
        return null;
    }
    public boolean stalemate(int[]kingCoords, int[][]checkingArray, int[][]opposingCheckingArray, String sideColor){
      Object originalTag, newTag;
        for(int j = 0; j < 8; j++){
            for(int k = 0; k < 8; k++){
                stalemateChecking = true;

                if(board[j][k].getTag() != null && board[j][k].getTag().toString().contains(sideColor)){
                    if (board[j][k].getTag().toString().equals("pawn_w") || board[j][k].getTag().toString().equals("pawn_*_w")) {
                        if(j+1<8 && k+1<8 && board[j+1][k+1].getTag()!=null && board[j+1][k+1].getTag().toString().contains("_b")) { //up right
                            piece = board[j][k];
                            moveTo = board[j+1][k+1];
                            originalTag = piece.getTag();
                            newTag = moveTo.getTag();
                            pawn_w();
                            if(!(blackCheckmate[kingCoords[2]][kingCoords[1]]>0)) {
                                return false;
                            }
                            reverseMove(j, k, j+1, k+1, originalTag, newTag);
                        }
                        Log.d("ERROR", "check 1");
                        if(j-1>=0 && k+1<8 && board[j-1][k+1].getTag()!=null && board[j-1][k+1].getTag().toString().contains("_b")) { //up left
                            capturingArray[j - 1][k + 1]++;
                            checkmateHelper(j-1, k+1, j, k, 0, sideColor);
                        }
                        Log.d("ERROR", "check 2");
                        if(k-1>=0 && board[j][k-1].getTag()==null) { //forward blocking
                            capturingArray[j][k - 1]++;
                            checkmateHelper(j, k+1, j, k, 1, sideColor);
                        }
                        Log.d("ERROR", "finished white pawn check");
                    }
                }



            }
        }



    }

    public void reverseMove(int j, int k, int newJ, int newK, Object originalTag, Object newTag){
        board[j][k].setTag(originalTag);
        board[newJ][newK].setTag(newTag);
        
    }

    public void promotions(View v){
        int buttonId = v.getId();
        ImageButton promotionChoice = (ImageButton)findViewById(buttonId);
        int pawnColumn = 0, pawnRow = 0;
        Log.d("ERROR","Entered Pawn Promotion");

        promoteKnight.setVisibility(View.VISIBLE);
        promoteBishop.setVisibility(View.VISIBLE);
        promoteQueen.setVisibility(View.VISIBLE);
        promoteRook.setVisibility(View.VISIBLE);
        pawnPromote.setVisibility(View.VISIBLE);

        if(moveWorB == false){ //inverted. white will be false
            Log.d("ERROR","Pawn is White");
            pawnRow = 7;
            for(int j=0; j<8; j++){
                if(board[j][pawnRow].getTag() != null && board[j][pawnRow].getTag().toString().contains("pawn")){
                    Log.d("ERROR","Pawn is found");
                    pawnColumn = j;
                    if(promotionChoice.getTag().equals("queen")){
                        board[pawnColumn][pawnRow].setTag("queen_w");
                        board[pawnColumn][pawnRow].setImageResource(R.drawable.queen_w);
                        Log.d("ERROR","pawn is promoted");
                    }
                    else if(promotionChoice.getTag().equals("rook")){
                        board[pawnColumn][pawnRow].setTag("rook_w");
                        board[pawnColumn][pawnRow].setImageResource(R.drawable.rook_w);
                    }
                    else if(promotionChoice.getTag().equals("bishop")){
                        board[pawnColumn][pawnRow].setTag("bishop_w");
                        board[pawnColumn][pawnRow].setImageResource(R.drawable.bishop_w);
                    }
                    else if(promotionChoice.getTag().equals("knight")){
                        board[pawnColumn][pawnRow].setTag("knight_w");
                        board[pawnColumn][pawnRow].setImageResource(R.drawable.knight_w);
                    }
                }
            }
        }
        else if(moveWorB == true){
            pawnRow = 0;
            for(int j=0; j<8; j++){
                if(board[j][pawnRow].getTag() != null && board[j][pawnRow].getTag().toString().contains("pawn")){
                    pawnColumn = j;
                    if(promotionChoice.getTag().equals("queen")){
                        board[pawnColumn][pawnRow].setTag("queen_b");
                        board[pawnColumn][pawnRow].setImageResource(R.drawable.queen_b);
                    }
                    else if(promotionChoice.getTag().equals("rook")){
                        board[pawnColumn][pawnRow].setTag("rook_b");
                        board[pawnColumn][pawnRow].setImageResource(R.drawable.rook_b);
                    }
                    else if(promotionChoice.getTag().equals("bishop")){
                        board[pawnColumn][pawnRow].setTag("bishop_w");
                        board[pawnColumn][pawnRow].setImageResource(R.drawable.bishop_b);
                    }
                    else if(promotionChoice.getTag().equals("knight")){
                        board[pawnColumn][pawnRow].setTag("knight_b");
                        board[pawnColumn][pawnRow].setImageResource(R.drawable.knight_b);
                    }
                }
            }
        }

        promoteRook.setVisibility(View.GONE);
        promoteKnight.setVisibility(View.GONE);
        promoteBishop.setVisibility(View.GONE);
        promoteQueen.setVisibility(View.GONE);
        pawnPromote.setVisibility(View.INVISIBLE);

    }
    public void colors(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piece.getId() == board[i][j].getId()) {
                    board[i][j].setBackgroundColor(getResources().getColor(R.color.purplePiece));
                }
            }
        }
    }

    //TODO: stalemate/draw
    //TODO: notation print
}