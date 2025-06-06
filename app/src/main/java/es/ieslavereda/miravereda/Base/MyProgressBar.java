package es.ieslavereda.miravereda.Base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

/**
 * Barra de progreso personalizada que se puede centrar y controlar
 * dentro de un ViewGroup raíz, ocultando o mostrando las demás vistas
 * mientras se muestra la barra.
 */
public class MyProgressBar extends ProgressBar {

    private ViewGroup rootView;

    /**
     * Constructor simple.
     *
     * @param context Contexto de la aplicación o actividad.
     */
    public MyProgressBar(Context context) {
        super(context);
    }

    /**
     * Constructor con atributos.
     *
     * @param context Contexto.
     * @param attrs   Atributos XML.
     */
    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor con atributos y estilo por defecto.
     *
     * @param context      Contexto.
     * @param attrs        Atributos XML.
     * @param defStyleAttr Estilo por defecto.
     */
    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Inicializa el control asignándolo a un ViewGroup raíz.
     * Añade la barra de progreso centrada en el rootView.
     *
     * @param rootView ViewGroup contenedor donde se añadirá la barra.
     */
    public void initControl(ViewGroup rootView) {
        this.rootView = rootView;
        center();
        this.rootView.addView(this);
    }

    /**
     * Muestra la barra de progreso y oculta todas las demás vistas del rootView.
     */
    public void show() {
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View child = rootView.getChildAt(i);
            if (!child.equals(this))
                child.setVisibility(View.GONE);
            else
                child.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Oculta la barra de progreso y vuelve a mostrar todas las demás vistas del rootView.
     */
    public void hide() {
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View child = rootView.getChildAt(i);
            if (!child.equals(this))
                child.setVisibility(View.VISIBLE);
            else
                child.setVisibility(View.GONE);
        }
    }

    /**
     * Centra la barra de progreso en el rootView.
     * Soporta rootView de tipo ConstraintLayout y RelativeLayout.
     */
    public void center() {
        if (rootView instanceof ConstraintLayout) {
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
            layoutParams.endToEnd = ConstraintSet.PARENT_ID;
            layoutParams.startToStart = ConstraintSet.PARENT_ID;
            layoutParams.topToTop = ConstraintSet.PARENT_ID;
            this.setLayoutParams(layoutParams);
        } else if (rootView instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            this.setLayoutParams(layoutParams);
        }
    }
}