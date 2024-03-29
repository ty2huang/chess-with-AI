package chess.pieces;
import chess.*;
import mytools.Coordinates;
import java.util.Set;

public class Rook extends Piece {

    private final boolean m_isRight;

    /** Constructor */
    public Rook(Board board, Coordinates rc, boolean isWhite) {
        super(board, rc, isWhite);
        m_type = Type.ROOK;
        m_power = 500;
        ReadFileToPositionValues("Rook.txt");
        m_isRight = (rc.m_col == 0) ? false : true;
    }

    /** Copy constructor */
    public Rook(Board board, Piece otherPiece) {
        super(board, otherPiece);
        Rook otherRook = (Rook) otherPiece;
        m_isRight = otherRook.m_isRight;
    }

    /** Adds all valid moves by this piece into set of validMoves */
    public void getValidMoves(Set<Coordinates> validMoves, boolean toMovePiece) {
        m_tempRC = m_rc;
        for (byte i = -1; i <= 1; i += 2) {
            addValidPath(validMoves, toMovePiece, i, 0);
            addValidPath(validMoves, toMovePiece, 0, i);
        }
        m_rc = m_tempRC;
    }

    /** Updates variables when this piece is moved */
    public void updateWhenMoved() {
        m_board.restrictCastling(m_isWhite, m_isRight);
    }

    /** Paints this piece on the board */
    public void paint() {
        super.paint();
        String utf8Sym = (m_isWhite) ? "\u265C" : "\u2656";
        System.out.print(utf8Sym);
    }
}