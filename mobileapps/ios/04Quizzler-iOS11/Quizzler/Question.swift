//
//  Question.swift
//  Quizzler
//
//  Created by Tung Nguyen Nguyen on 11/20/18.
//  Copyright © 2018 London App Brewery. All rights reserved.
//

import Foundation

class Question {
    let questionText: String
    let answer: Bool
    
    init(question: String, correctAnswer: Bool) {
        self.questionText = question
        self.answer = correctAnswer
    }
}
