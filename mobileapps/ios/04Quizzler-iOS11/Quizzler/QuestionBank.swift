//
//  QuestionBank.swift
//  Quizzler
//
//  Created by Tung Nguyen Nguyen on 11/20/18.
//  Copyright © 2018 London App Brewery. All rights reserved.
//

import Foundation

class QuestionBank {
    var list = [Question]()
    
    init() {
        // Creating a quiz item and appending it to the list
        let item = Question(question: "Valentine\'s day is banned in Saudi Arabia.", correctAnswer: true)
        
        // Add the Question to the list of questions
        list.append(item)
        
        // skipping one step and just creating the quiz item inside the append function
        list.append(Question(question: "A slug\'s blood is green.", correctAnswer: true))
        
        list.append(Question(question: "Approximately one quarter of human bones are in the feet.", correctAnswer: true))
        
        list.append(Question(question: "The total surface area of two human lungs is approximately 70 square metres.", correctAnswer: true))
        
        list.append(Question(question: "In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.", correctAnswer: true))
        
        list.append(Question(question: "In London, UK, if you happen to die in the House of Parliament, you are technically entitled to a state funeral, because the building is considered too sacred a place.", correctAnswer: false))
        
        list.append(Question(question: "It is illegal to pee in the Ocean in Portugal.", correctAnswer: true))
        
        list.append(Question(question: "You can lead a cow down stairs but not up stairs.", correctAnswer: false))
        
        list.append(Question(question: "Google was originally called \"Backrub\".", correctAnswer: true))
        
        list.append(Question(question: "Buzz Aldrin\'s mother\'s maiden name was \"Moon\".", correctAnswer: true))
        
        list.append(Question(question: "The loudest sound produced by any animal is 188 decibels. That animal is the African Elephant.", correctAnswer: false))
        
        list.append(Question(question: "No piece of square dry paper can be folded in half more than 7 times.", correctAnswer: false))
        
        list.append(Question(question: "Chocolate affects a dog\'s heart and nervous system; a few ounces are enough to kill a small dog.", correctAnswer: true))
    }
}
