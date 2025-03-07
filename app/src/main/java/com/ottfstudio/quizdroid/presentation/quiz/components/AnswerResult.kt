package com.ottfstudio.quizdroid.presentation.quiz.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ottfstudio.quizdroid.R
import com.ottfstudio.quizdroid.presentation.quiz.model.ResultData
import com.ottfstudio.quizdroid.ui.theme.Green100
import com.ottfstudio.quizdroid.ui.theme.Green40
import com.ottfstudio.quizdroid.ui.theme.Red100
import com.ottfstudio.quizdroid.ui.theme.Red500

@Composable
fun AnswerResult(
    isCorrect: Boolean,
    modifier: Modifier = Modifier,
) {
    val resultData = if (isCorrect) {
        ResultData(
            text = stringResource(R.string.result_correct),
            textColor = Green100,
            icon = R.drawable.ic_check,
            iconSize = 13.0.dp,
            boxColor = Green40,
        )
    } else {
        ResultData(
            text = stringResource(R.string.result_wrong),
            textColor = Red500,
            icon = R.drawable.ic_x,
            iconSize = 10.0.dp,
            boxColor = Red100,
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = resultData.boxColor,
                    shape = CircleShape,
                ),
        ) {
            Image(
                painter = painterResource(id = resultData.icon),
                contentDescription = resultData.text,
                modifier = Modifier.size(resultData.iconSize),
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = resultData.text,
            color = resultData.textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
